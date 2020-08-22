INSERT INTO skill(name) SELECT ('Java') WHERE NOT EXISTS (SELECT name FROM skill WHERE name='Java');
INSERT INTO skill(name) SELECT ('PHP') WHERE NOT EXISTS (SELECT name FROM skill WHERE name = 'PHP');
INSERT INTO skill(name) SELECT ('Mobile') WHERE NOT EXISTS (SELECT name FROM skill WHERE name='Mobile');
INSERT INTO skill(name) SELECT ('HTML') WHERE NOT EXISTS (SELECT name FROM skill WHERE name='HTML');
INSERT INTO skill(name) SELECT ('Test') WHERE NOT EXISTS (SELECT name FROM skill WHERE name='Test'); 