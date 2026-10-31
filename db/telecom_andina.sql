DROP DATABASE IF EXISTS telecom_andina;
CREATE DATABASE telecom_andina;
USE telecom_andina;

CREATE TABLE sede (
  idsede INT PRIMARY KEY,
  nombre VARCHAR(45) NOT NULL
);

CREATE TABLE equipo (
  idequipo INT PRIMARY KEY AUTO_INCREMENT,
  modelo VARCHAR(60) NOT NULL,
  tipo VARCHAR(10) NOT NULL,  -- 'SWITCH' o 'AP'
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL,
  idsede INT NOT NULL,
  FOREIGN KEY (idsede) REFERENCES sede(idsede)
);

INSERT INTO sede VALUES (1,'Lima'),(2,'Arequipa'),(3,'Trujillo');

INSERT INTO equipo (modelo,tipo,precio,cantidad,idsede) VALUES
('Catalyst C9200L-24P','SWITCH',2500.00,4,1),
('Catalyst C9300-48P','SWITCH',7800.00,2,1),
('Catalyst 9120AXI','AP',950.00,12,1),
('Catalyst C9200L-48P','SWITCH',3900.00,2,2),
('Catalyst 9115AXI','AP',780.00,8,2),
('Catalyst C9200L-24T','SWITCH',2100.00,1,3),
('Catalyst 9105AXI','AP',520.00,6,3),
('Catalyst 9130AXI','AP',1300.00,3,3);
