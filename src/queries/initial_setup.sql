START TRANSACTION;

-- Entidades

CREATE TABLE IF NOT EXISTS curso (
	codigo INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
	anio INT  NOT NULL,
);

CREATE TABLE IF NOT EXISTS alumno (
	rut	VARCHAR(15) NOT NULL PRIMARY KEY,
	nombres VARCHAR(50) NOT NULL,
	apellido_paterno VARCHAR(20) NOT NULL,
	apellido_materno VARCHAR(20) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	ciudad VARCHAR(20) NOT NULL,
	codigo_curso INT  NOT NULL REFERENCES curso(codigo) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS profesor (
	rut	VARCHAR(15) NOT NULL PRIMARY KEY,
	nombres VARCHAR(50) NOT NULL,
	apellido_paterno VARCHAR(20) NOT NULL,
	apellido_materno VARCHAR(20) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	ciudad VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS extraprogramatica (
	codigo INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(20) NOT NULL,
	dia INT(1)  NOT NULL,
	hora_inicio INT  NOT NULL,
	hora_fin INT  NOT NULL,
	cupos INT UNSINGED NOT NULL,
	lugar VARCHAR(50) NOT NULL,
	rut_profesor VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES profesor(rut) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS apoderado (
	rut	VARCHAR(15) NOT NULL PRIMARY KEY,
	nombres VARCHAR(50) NOT NULL,
	apellido_paterno VARCHAR(20) NOT NULL,
	apellido_materno VARCHAR(20) NOT NULL,
	direccion VARCHAR(50) NOT NULL,
	ciudad VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS especialidad (
	codigo_especialidad INT  NOT NULL PRIMARY KEY REFERENCES especialidad(codigo),
	descripcion VARCHAR(50) NOT NULL
);

-- Relaciones

CREATE TABLE IF NOT EXISTS media (
	codigo_curso INT  NOT NULL PRIMARY KEY REFERENCES curso(codigo) ON DELETE CASCADE,
	orientacion VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS basica (
	codigo_curso INT  NOT NULL PRIMARY KEY REFERENCES curso(codigo) ON DELETE CASCADE,
);

CREATE TABLE IF NOT EXISTS es_jefe (
	codigo_curso INT  NOT NULL PRIMARY KEY REFERENCES curso(codigo) ON DELETE CASCADE,
	rut_profesor_jefe VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES profesor(rut) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS es_asistente (
	codigo_curso INT  NOT NULL PRIMARY KEY REFERENCES curso(codigo) ON DELETE CASCADE,
	rut_profesor_asistente VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES profesor(rut) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS participa (
	rut_alumno VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES alumno(rut) ON DELETE CASCADE,
	codigo INT  NOT NULL PRIMARY KEY REFERENCES extraprogramatica(codigo) ON DELETE CASCADE,
);

CREATE TABLE IF NOT EXISTS representa (
	rut_alumno VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES alumno(rut) ON DELETE CASCADE,
	rut_apoderado VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES apoderado(rut) ON DELETE CASCADE,
	fecha_inicio DATE NOT NULL,
	fecha_termino DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tiene (
	rut_profesor VARCHAR(15) NOT NULL PRIMARY KEY REFERENCES profesor(rut) ON DELETE CASCADE,
	codigo_especialidad INT  NOT NULL PRIMARY KEY REFERENCES especialidad(codigo) ON DELETE CASCADE
);


COMMIT;