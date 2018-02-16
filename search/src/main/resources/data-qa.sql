INSERT INTO search_inventory (inv_id, count, created_by, updated_by, created_time, updated_time, enabled) VALUES
  (1, 100, 'admin', 'admin', now(), now(), 1),
  (2, 100, 'admin', 'admin', now(), now(), 1),
  (3, 100, 'admin', 'admin', now(), now(), 1),
  (4, 100, 'admin', 'admin', now(), now(), 1),
  (5, 100, 'admin', 'admin', now(), now(), 1),
  (6, 100, 'admin', 'admin', now(), now(), 1),
  (7, 100, 'admin', 'admin', now(), now(), 1);

INSERT INTO search_price (price_id, currency, price_amount, created_by, updated_by, created_time, updated_time, enabled) VALUES
  (1, 'USD', 100, 'admin', 'admin', now(), now(), 1),
  (2, 'USD', 101, 'admin', 'admin', now(), now(), 1),
  (3, 'USD', 102, 'admin', 'admin', now(), now(), 1),
  (4, 'USD', 103, 'admin', 'admin', now(), now(), 1),
  (5, 'USD', 104, 'admin', 'admin', now(), now(), 1),
  (6, 'USD', 105, 'admin', 'admin', now(), now(), 1),
  (7, 'USD', 106, 'admin', 'admin', now(), now(), 1);

INSERT INTO search_trip (id, bus_number, destination, origin, trip_date, inv_id, price_id, created_by, updated_by, created_time, updated_time, enabled) VALUES
  (1, 'BF100', 'SFO', 'SEA', '22-JAN-16', 1, 1, 'admin', 'admin', now(), now(), 1),
  (2, 'BF101', 'SFO', 'NYC', '22-JAN-16', 2, 2, 'admin', 'admin', now(), now(), 1),
  (3, 'BF102', 'SFO', 'CHI', '22-JAN-16', 3, 3, 'admin', 'admin', now(), now(), 1),
  (4, 'BF103', 'SFO', 'HOU', '22-JAN-16', 4, 4, 'admin', 'admin', now(), now(), 1),
  (5, 'BF104', 'SFO', 'LAX', '22-JAN-16', 5, 5, 'admin', 'admin', now(), now(), 1),
  (6, 'BF105', 'SFO', 'NYC', '22-JAN-16', 6, 6, 'admin', 'admin', now(), now(), 1),
  (7, 'BF106', 'SFO', 'NYC', '22-JAN-16', 7, 7, 'admin', 'admin', now(), now(), 1);