#!/usr/bin/env bash

RABBITMQ_MANAGEMENT_SERVICE_NAME="rabbitmq-management"
RABBITMQ_MANAGEMENT_SERVICE_PORT=30010
RABBITMQ_SERVICE_NAME="rabbitmq"

echo "Parsing input arguments"

UNKNOWN=()
while [[ $# -gt 0 ]]
do
    flag="$1"

    case ${flag} in
        -n|--namespace)
        echo "Set namespace to: " "$2"
        export KUBERNETES_NAMESPACE="$2"
        shift # past argument
        shift # past value
        ;;

        -st|--skipTests)
        echo "Set -DskipTests flag to: TRUE"
        SKIP_TESTS="true"
        shift # past argument
        ;;

        -qd|--quietDeploy)
        echo "Set quiet deploy to: TRUE"
        QUIET_DEPLOY="true"
        shift # past argument
        ;;

        -m|--memory)
        echo "Set cluster RAM to: $2"
        CLUSTER_RAM=$2
        shift # past argument
        shift # past value
        ;;

        *)    # unknown option
        echo "Unknown parameter: " "$1"
        UNKNOWN+=("$1") # save it in an array for later
        shift # past argument
        ;;
    esac
done

function check_minikube_status() {

    echo "Checking minikube status"

    if [ -z "$(minikube status | grep "minikube: Running")" ]; then

        echo "Starting minikube cluster"
        if [ -z ${CLUSTER_RAM} ]; then
            minikube start
        else
            minikube start --memory ${CLUSTER_RAM}
        fi

    fi
}

function export_global_variables() {

    echo "Export docker-env variables"
    eval $(minikube docker-env)
}

function switch_minikube_context() {

    NAMESPACE_STATUS="$(kubectl get namespace | grep "${KUBERNETES_NAMESPACE}")"

    if [ -z "${NAMESPACE_STATUS}" ]; then
       echo "Create namespace "${KUBERNETES_NAMESPACE}""
       echo "$(kubectl create namespace "${KUBERNETES_NAMESPACE}")";
    fi

    echo "Switch minikube context to "${KUBERNETES_NAMESPACE}""
    kubectl config set-context $(kubectl config current-context) --namespace=${KUBERNETES_NAMESPACE}
}

function check_rabbitmq_config() {

    echo "Checking rabbitmq config"

    CHECK_RABBITMQ_CONFIG="$(kubectl get secrets | grep rabbitmq-config)"
    if [ -z  "$CHECK_RABBITMQ_CONFIG" ]; then
        echo "Adding rabbitmq config"
        kubectl create secret generic rabbitmq-config --from-literal=erlang-cookie=c-is-for-cookie-thats-good-enough-for-me
    fi
}

function start_rabbitmq() {

    check_rabbitmq_config

    if [ -n  "$(kubectl get sts | grep ${RABBITMQ_SERVICE_NAME})" ]; then
        echo "Delete rabbitmq stateful set"
        kubectl delete sts ${RABBITMQ_SERVICE_NAME}
    fi

    echo "Starting rabbitmq"

    if [ -z  "$(kubectl get svc | grep ${RABBITMQ_SERVICE_NAME})" ]; then
        echo "Create rabbitmq service"
        kubectl create -f rabbitmq-svc.yaml
    else
        echo "Refresh rabbitmq service"
        kubectl apply -f rabbitmq-svc.yaml
    fi
}

function set_config_map() {

    SERVICE_NAME=$1
    SERVICE_PATH=$2

    CONFIG_MAP=""${SERVICE_PATH}"/src/main/k8s/config/${KUBERNETES_NAMESPACE}.yaml"

    if [ -z "$(kubectl get configmap ${SERVICE_NAME})" ]; then
        echo "Create config map from path: "${CONFIG_MAP}""
        kubectl create -f ${CONFIG_MAP}
    else
        echo "Refresh config map from path: "${CONFIG_MAP}""
        kubectl apply -f ${CONFIG_MAP}
    fi

}

function deploy() {

    SERVICE_NAME=$1
    SERVICE_PATH=$2
    SERVICE_DEPLOY_NAME=${SERVICE_PATH}
    SERVICE_PORT=$3

#    stop existing deployment
    if [ -n "$(kubectl get deploy | grep ${SERVICE_DEPLOY_NAME})" ]; then
        echo "Undeploy "${SERVICE_DEPLOY_NAME}""
        kubectl delete deployment ${SERVICE_DEPLOY_NAME}
    fi

    echo "Run service ${SERVICE_NAME}"

    if [ -n ${QUIET_DEPLOY} ]; then
        mvn -f ${SERVICE_PATH} clean fabric8:run --quiet -DskipTests=${SKIP_TESTS} -Dfabric8.onExit=undeploy &
    else
        mvn -f ${SERVICE_PATH} clean fabric8:run -DskipTests=${SKIP_TESTS} -Dfabric8.onExit=undeploy &
    fi

    HOST_ADDRESS=$(minikube ip)

    echo "Wait until service "${SERVICE_NAME}" is not up"
    until $(curl --output /dev/null --silent --head --fail http://${HOST_ADDRESS}:${SERVICE_PORT}); do
        printf '.'
        sleep 5
    done

    echo "Service "${SERVICE_NAME}" is UP"
}

function start_service() {

    SERVICE_NAME=$1
    SERVICE_PATH=$2
    SERVICE_PORT=$3

    echo "Starting up service: "${SERVICE_NAME}""

    set_config_map ${SERVICE_NAME} ${SERVICE_PATH}
    deploy ${SERVICE_NAME} ${SERVICE_PATH} ${SERVICE_PORT}
}

function start_up_services() {

    echo "Starting up all services"

#-----------------------------------------------------------------------------------
#   actual start up
#-----------------------------------------------------------------------------------
#    start_service "webface-service"     "webface"   30001
#    start_service "search-service"      "search"    30002
#    start_service "book-service"        "book"      30003
#    start_service "checkin-service"     "checkin"   30004
    start_service "prices-service"      "prices"    30005
#-----------------------------------------------------------------------------------

    echo "Starting up all services: completed"
}

check_minikube_status
export_global_variables
switch_minikube_context
start_rabbitmq
start_up_services