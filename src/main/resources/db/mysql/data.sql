-- Insert Data

-- Authorities
INSERT INTO authority (name) VALUES
                                 ('ROLE_USER'),
                                 ('ROLE_ADMIN');

-- Categories
INSERT INTO category (name, description) VALUES
                                             ('INFORMATIQUE', 'Maîtriser les outils informatiques , TIC, gestion des systèmes - réseau, développement, méthodologie, base de données'),
                                             ('Bureautique', 'Acquérir des compétences professionnelles variées et d''apprendre à se servir des différents logiciels de bureautique sur un ordinateur'),
                                             ('DIGITAL & MULTIMEDIA', 'Accéder à une douzaine de métiers dans le numérique, axés sur la communication en ligne, le développement web'),
                                             ('MANAGEMENT & SOFT SKILLS', 'Maîtriser la communication, la gestion du temps, la résolution de problèmes et le leadership'),
                                             ('COMPETENCES METIER', 'Développer des connaissances, des compétences opérationnelles et des compétence comportementale'),
                                             ('UNIVERS PROGESSIONNEL', 'acquérir le savoir, le savoir-faire et le savoir-être ,capacité et aptitude');
-- Roles
INSERT INTO role (name) VALUES
                            ('ROLE_USER'),
                            ('ROLE_ADMIN');

-- Users
INSERT INTO user (
    account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled,
    username,
    password,
    email
) VALUES
      (true, true, true, true, 'user', '{noop}password', 'user@example.com'),
      (true, true, true, true, 'admin', '{noop}password', 'admin@example.com');

-- User Roles
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1),
                                              (2, 2);

-- Instructors
INSERT INTO instructor (first_name, last_name, email, bio) VALUES
                                                               ('John', 'Doe', 'john.doe@gmail.com', '10 d''expérience dans la programmation JAVA.'),
                                                               ('Michel', 'Mickael', 'michel.mickael@gmail.com', '5 ans dans PHP.');
                                                               ('Jonathan', 'Michel', 'jonathan.mickael@gmail.com', ' developpeur Spring Boot.');
                                                               ('Hakim', 'Saoudi', 'hakim.saoudi@gmail.com', 'Concepteur Angular.');
                                                               ('Colignon', 'Audrey', 'colignon.audrey@gmail.com', 'developpeuse symfony.');
-- Trainings
ALTER TABLE training CHANGE COLUMN category_id training_category_id INT;

INSERT INTO training (name, description, image, training_category_id ) VALUES
                                                    ('Agilité', 'Maitrise de la methode agile et le travaille en équipe ', 'agile.jpg', 'Informatique'),
                                                    ('Bases de données', 'Maitrise de la gestion et la conception de la BD et les languages adaptés ', 'bd.jpg', 'Informatique'),
                                                    ('Logiciels Bureautique', 'Maitrise des logiciels Bureautique ', 'lb.jpg', 'Bureautique'),
                                                    ('Retouches image', 'Modifier les image, supprimer les défauts et faire des montages ', 'retouchImg.jpg', 'Bureautique'),

-- Courses
INSERT INTO course (
    title,
    description,
    instructor_id,
    start_date,
    end_date,
    location,
    online,
    price,
    category_id
) VALUES
      ('Java Basics', 'Introduction to Java programming.', 1, '2023-01-01', '2023-02-01', 'Online', true, 100.0, 1),
      ('Advanced Java', 'Advanced topics in Java programming.', 1, '2023-02-01', '2023-03-01', 'Online', true, 150.0, 1),
      ('Web Design Fundamentals', 'Essentials of web design.', 2, '2023-01-15', '2023-02-15', 'Online', true, 120.0, 2);
