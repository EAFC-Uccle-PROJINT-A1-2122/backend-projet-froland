INSERT INTO section (id, name, version) VALUES (nextval('hibernate_sequence'), 'Bachelier en informatique de gestion', 1);
INSERT INTO section (id, name, version) VALUES (nextval('hibernate_sequence'), 'Bachelier en informatique et systèmes', 1);
INSERT INTO section (id, name, version) VALUES (nextval('hibernate_sequence'), 'Bachelier en construction', 1);
INSERT INTO section (id, name, version) VALUES (nextval('hibernate_sequence'), 'Bachelier en électromécanique', 1);

INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Mathématiques appliquées à l''informatique', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Eléments de statistique', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Information et communication professionnelle', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Initiation aux bases de données', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Bases des réseaux', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Produits logiciels de gestion intégrés', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Notions de e-business', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Structure des ordinateurs', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Organisation des entreprises et éléments de management', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Epreuve intégrée', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Techniques de gestion de projet', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Stage d''intégration', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Activités professionnelles de formation', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Principes d''analyse informatique', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Projet d''analyse et de conception', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Principes algorithmiques et programmation', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Programmation orientée objet', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Administration, getsion et sécurisation des réseaux', 1);
INSERT INTO course (id, name, version) VALUES (nextval('hibernate_sequence'), 'Veille technologique', 1);

INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Mathématiques appliquées à l''informatique';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Eléments de statistique';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Information et communication professionnelle';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Initiation aux bases de données';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Bases des réseaux';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Produits logiciels de gestion intégrés';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Notions de e-business';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Structure des ordinateurs';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Organisation des entreprises et éléments de management';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Epreuve intégrée';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Techniques de gestion de projet';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Stage d''intégration';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Activités professionnelles de formation';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Principes d''analyse informatique';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Projet d''analyse et de conception';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Principes algorithmiques et programmation';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Programmation orientée objet';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Administration, getsion et sécurisation des réseaux';
INSERT INTO section_course (section_id, course_id) SELECT section.id, course.id FROM section, course WHERE section.name = 'Bachelier en informatique de gestion' AND course.name = 'Veille technologique';
