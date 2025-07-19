START TRANSACTION;

ALTER TABLE curso ADD CONSTRAINT PK_curso PRIMARY KEY (codigo);
ALTER TABLE alumno ADD CONSTRAINT PK_alumno PRIMARY KEY (rut);
ALTER TABLE profesor ADD CONSTRAINT PK_profesor PRIMARY KEY (rut);
ALTER TABLE extraprogramatica ADD CONSTRAINT PK_extraprogramatica PRIMARY KEY (codigo);
ALTER TABLE apoderado ADD CONSTRAINT PK_apoderado PRIMARY KEY (rut);
ALTER TABLE especialidad ADD CONSTRAINT PK_especialidad PRIMARY KEY (codigo);
ALTER TABLE media ADD CONSTRAINT PK_media PRIMARY KEY (codigo_curso);
ALTER TABLE basica ADD CONSTRAINT PK_basica PRIMARY KEY (codigo_curso);
ALTER TABLE es_jefe ADD CONSTRAINT PK_es_jefe PRIMARY KEY (codigo_curso, rut_profesor_jefe);
ALTER TABLE es_asistente ADD CONSTRAINT PK_es_asistente PRIMARY KEY (codigo_curso, rut_profesor_asistente);
ALTER TABLE participa ADD CONSTRAINT PK_participa PRIMARY KEY (rut_alumno, codigo);
ALTER TABLE representa ADD CONSTRAINT PK_representa PRIMARY KEY (rut_alumno, rut_apoderado);
ALTER TABLE tiene ADD CONSTRAINT PK_tiene PRIMARY KEY (rut_profesor, codigo_especialidad);

COMMIT;