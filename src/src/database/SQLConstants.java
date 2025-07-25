/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package database;

/**
 *
 * @author Roger
 */
public interface SQLConstants {
    public static final String INITIAL_QUERY = """
        -- Entidades

        CREATE TABLE IF NOT EXISTS curso (
                codigo INT  NOT NULL,
                anio INT  NOT NULL
        );

        ALTER TABLE curso ADD CONSTRAINT PK_curso PRIMARY KEY (codigo);

        CREATE TABLE IF NOT EXISTS alumno (
                rut	VARCHAR(15) NOT NULL,
                nombres VARCHAR(50) NOT NULL,
                apellido_paterno VARCHAR(20) NOT NULL,
                apellido_materno VARCHAR(20) NOT NULL,
                fecha_nacimiento DATE NOT NULL,
                direccion VARCHAR(50) NOT NULL,
                ciudad VARCHAR(20) NOT NULL,
                codigo_curso INT  NOT NULL REFERENCES curso(codigo) 
        );

        ALTER TABLE alumno ADD CONSTRAINT PK_alumno PRIMARY KEY (rut);

        CREATE TABLE IF NOT EXISTS profesor (
                rut	VARCHAR(15) NOT NULL,
                nombres VARCHAR(50) NOT NULL,
                apellido_paterno VARCHAR(20) NOT NULL,
                apellido_materno VARCHAR(20) NOT NULL,
                direccion VARCHAR(50) NOT NULL,
                ciudad VARCHAR(20) NOT NULL
        );

        ALTER TABLE profesor ADD CONSTRAINT PK_profesor PRIMARY KEY (rut);

        CREATE TABLE IF NOT EXISTS extraprogramatica (
                codigo INT  NOT NULL,
                nombre VARCHAR(20) NOT NULL,
                dia INT  NOT NULL,
                hora_inicio INT  NOT NULL,
                hora_fin INT  NOT NULL,
                cupos INT  NOT NULL,
                lugar VARCHAR(50) NOT NULL,
                rut_profesor VARCHAR(15) NOT NULL REFERENCES profesor(rut) 
        );

        ALTER TABLE extraprogramatica ADD CONSTRAINT PK_extraprogramatica PRIMARY KEY (codigo);

        CREATE TABLE IF NOT EXISTS apoderado (
                rut	VARCHAR(15) NOT NULL,
                nombres VARCHAR(50) NOT NULL,
                apellido_paterno VARCHAR(20) NOT NULL,
                apellido_materno VARCHAR(20) NOT NULL,
                direccion VARCHAR(50) NOT NULL,
                ciudad VARCHAR(20) NOT NULL
        );

        ALTER TABLE apoderado ADD CONSTRAINT PK_apoderado PRIMARY KEY (rut);

        CREATE TABLE IF NOT EXISTS especialidad (
                codigo INT  NOT NULL,
                descripcion VARCHAR(50) NOT NULL
        );

        ALTER TABLE especialidad ADD CONSTRAINT PK_especialidad PRIMARY KEY (codigo);

        -- Relaciones

        CREATE TABLE IF NOT EXISTS media (
                codigo_curso INT  NOT NULL REFERENCES curso(codigo) ,
                orientacion VARCHAR(20) NOT NULL
        );

        ALTER TABLE media ADD CONSTRAINT PK_media PRIMARY KEY (codigo_curso);

        CREATE TABLE IF NOT EXISTS basica (
                codigo_curso INT  NOT NULL REFERENCES curso(codigo) 
        );

        ALTER TABLE basica ADD CONSTRAINT PK_basica PRIMARY KEY (codigo_curso);

        CREATE TABLE IF NOT EXISTS es_jefe (
                codigo_curso INT  NOT NULL REFERENCES curso(codigo) ,
                rut_profesor_jefe VARCHAR(15) NOT NULL REFERENCES profesor(rut) 
        );

        ALTER TABLE es_jefe ADD CONSTRAINT PK_es_jefe PRIMARY KEY (codigo_curso, rut_profesor_jefe);

        CREATE TABLE IF NOT EXISTS es_asistente (
                codigo_curso INT  NOT NULL REFERENCES basica(codigo_curso) ,
                rut_profesor_asistente VARCHAR(15) NOT NULL REFERENCES profesor(rut) 
        );

        ALTER TABLE es_asistente ADD CONSTRAINT PK_es_asistente PRIMARY KEY (codigo_curso, rut_profesor_asistente);

        CREATE TABLE IF NOT EXISTS participa (
                rut_alumno VARCHAR(15) NOT NULL REFERENCES alumno(rut) ,
                codigo INT  NOT NULL REFERENCES extraprogramatica(codigo) 
        );

        ALTER TABLE participa ADD CONSTRAINT PK_participa PRIMARY KEY (rut_alumno, codigo);

        CREATE TABLE IF NOT EXISTS representa (
                rut_alumno VARCHAR(15) NOT NULL REFERENCES alumno(rut) ,
                rut_apoderado VARCHAR(15) NOT NULL REFERENCES apoderado(rut) ,
                fecha_inicio DATE NOT NULL,
                fecha_termino DATE NOT NULL
        );

        ALTER TABLE representa ADD CONSTRAINT PK_representa PRIMARY KEY (rut_alumno, rut_apoderado);

        CREATE TABLE IF NOT EXISTS tiene (
                rut_profesor VARCHAR(15) NOT NULL REFERENCES profesor(rut) ,
                codigo_especialidad INT  NOT NULL REFERENCES especialidad(codigo) 
        );

        ALTER TABLE tiene ADD CONSTRAINT PK_tiene PRIMARY KEY (rut_profesor, codigo_especialidad);              
    
        CREATE OR REPLACE VIEW alumnosXcurso AS (
        	SELECT c.codigo, COALESCE(s.cantidad, 0) AS cantidad FROM curso c LEFT JOIN (SELECT a.codigo_curso as codigo, COUNT(a.rut) as cantidad FROM alumno a GROUP BY a.codigo_curso) s ON c.codigo = s.codigo
        );
    """;
    
    public static final String INITIAL_TABLE_CHECK = 
    """
        SELECT a.rut, a.nombres, a.apellido_paterno, a.apellido_materno, a.fecha_nacimiento, a.direccion, a.ciudad, a.codigo_curso FROM alumno a LIMIT 0;
        SELECT a.rut, a.nombres, a.apellido_paterno, a.apellido_materno, a.direccion, a.ciudad FROM apoderado a LIMIT 0;
        SELECT b.codigo_curso FROM basica b LIMIT 0;
        SELECT c.codigo, c.anio FROM curso c LIMIT 0;
        SELECT e.codigo_curso, e.rut_profesor_asistente FROM es_asistente e LIMIT 0;
        SELECT e.codigo_curso, e.rut_profesor_jefe FROM es_jefe e LIMIT 0;
        SELECT e.codigo, e.descripcion FROM especialidad e LIMIT 0;
        SELECT e.codigo, e.nombre, e.dia, e.hora_inicio, e.hora_fin, e.cupos, e.lugar, e.rut_profesor FROM extraprogramatica e LIMIT 0;
        SELECT m.codigo_curso, m.orientacion FROM media m LIMIT 0;
        SELECT p.rut_alumno, p.codigo FROM participa p LIMIT 0;
        SELECT p.rut, p.nombres, p.apellido_paterno, p.apellido_materno, p.direccion, p.ciudad FROM profesor p LIMIT 0;
        SELECT r.rut_alumno, r.rut_apoderado, r.fecha_inicio, r.fecha_termino FROM representa r LIMIT 0;
        SELECT t.rut_profesor, t.codigo_especialidad FROM tiene t LIMIT 0;
        SELECT a.codigo, a.cantidad FROM alumnosXcurso a LIMIT 0;
    """;
}
