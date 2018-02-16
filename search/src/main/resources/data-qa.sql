INSERT INTO search_inventory (inv_id, count) VALUES
  (1, 100),
  (2, 100),
  (3, 100),
  (4, 100),
  (5, 100),
  (6, 100),
  (7, 100);

INSERT INTO search_price (price_id, currency, price_amount) VALUES
  (1, 'USD', 100),
  (2, 'USD', 101),
  (3, 'USD', 102),
  (4, 'USD', 103),
  (5, 'USD', 104),
  (6, 'USD', 105),
  (7, 'USD', 106);

INSERT INTO search_trip (id, bus_number, destination, origin, trip_date, inv_id, price_id) VALUES
  (1, 'BF100', 'SFO', 'SEA', '22-JAN-16', 1, 1),
  (2, 'BF101', 'SFO', 'NYC', '22-JAN-16', 2, 2),
  (3, 'BF102', 'SFO', 'CHI', '22-JAN-16', 3, 3),
  (4, 'BF103', 'SFO', 'HOU', '22-JAN-16', 4, 4),
  (5, 'BF104', 'SFO', 'LAX', '22-JAN-16', 5, 5),
  (6, 'BF105', 'SFO', 'NYC', '22-JAN-16', 6, 6),
  (7, 'BF106', 'SFO', 'NYC', '22-JAN-16', 7, 7);
