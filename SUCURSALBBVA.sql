
/*
"SucursalBancomer"

Base de datos:
Tabla EMPLEADOS
Campos ID_EMPLEADO(AUTOINCREMENTABLE), NSS, NOMBRE, APP, APM, SUELDO

Crear el proyecto de spring boot y de tip maven
Estar atentos al agregar las dependencias
Configurar el aplication.properties
Correr el proyecto
Logica de la aplicacion:
Crear paquetes siguiendo el modelo mvc
Realizar las siguientes peticiones y probarlos en postman:
    Get---Mostrar los empleados
    Post---Guardar empleado --- validar que el nss no se repita
    Post---Buscar empleado por id
    Put---Editar los datos del empleado
    Delete---Eliminar empleados por id
Subir el proyecto a github y compartir la liga de su repositorio al grupo de wasap
*/

-- 1. Limpiar por si ya existen de intentos pasados
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE EMPLEADOS CASCADE CONSTRAINTS';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/
BEGIN
    EXECUTE IMMEDIATE 'DROP SEQUENCE SEQ_EMPLEADO';
EXCEPTION WHEN OTHERS THEN NULL;
END;
/

-- 2. Crear la tabla EMPLEADOS con los campos pedidos
CREATE TABLE EMPLEADOS (
    ID_EMPLEADO INT PRIMARY KEY,
    NSS VARCHAR2(20) NOT NULL,
    NOMBRE VARCHAR2(50) NOT NULL,
    APP VARCHAR2(50) NOT NULL,
    APM VARCHAR2(50) NOT NULL,
    SUELDO NUMBER(10, 2) NOT NULL
);

-- 3. Crear la Secuencia para el autoincrementable
CREATE SEQUENCE SEQ_EMPLEADO 
START WITH 1 
INCREMENT BY 1;

-- 4. Crear el Trigger para automatizar el incremento al insertar
CREATE OR REPLACE TRIGGER TRG_EMPLEADOS
BEFORE INSERT ON EMPLEADOS
FOR EACH ROW
BEGIN
    IF :NEW.ID_EMPLEADO IS NULL THEN
        SELECT SEQ_EMPLEADO.NEXTVAL INTO :NEW.ID_EMPLEADO FROM DUAL;
    END IF;
END;
/

INSERT INTO EMPLEADOS (NSS, NOMBRE, APP, APM, SUELDO) 
VALUES ('1122334455', 'CARLOS', 'GARCIA', 'LOPEZ', 9500.00);

INSERT INTO EMPLEADOS (NSS, NOMBRE, APP, APM, SUELDO) 
VALUES ('2233445566', 'MARIA', 'RODRIGUEZ', 'PEREZ', 12300.50);

INSERT INTO EMPLEADOS (NSS, NOMBRE, APP, APM, SUELDO) 
VALUES ('3344556677', 'JORGE', 'HERNANDEZ', 'RUIZ', 11000.00);

INSERT INTO EMPLEADOS (NSS, NOMBRE, APP, APM, SUELDO) 
VALUES ('4455667788', 'ANA', 'MARTINEZ', 'SANCHEZ', 14500.00);

COMMIT;

-- Consulta rápida para verificar que se guardaron bien
SELECT * FROM EMPLEADOS;