-- Respaldo. Ejecutar en SSMS solo si hace falta. Antes: CREATE DATABASE minimarket_db;
USE minimarket_db;
GO

CREATE TABLE usuario (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    clave VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    rol VARCHAR(20) NOT NULL
        CHECK (rol IN ('ADMIN','SUPERVISOR','CAJERO','CLIENTE')),
    activo BIT NOT NULL DEFAULT 1
);

CREATE TABLE producto (
    id INT IDENTITY(1,1) PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    stock INT NOT NULL DEFAULT 0 CHECK (stock >= 0)
);

CREATE TABLE venta (
    id INT IDENTITY(1,1) PRIMARY KEY,
    fecha DATETIME NOT NULL DEFAULT GETDATE(),
    id_cajero INT NOT NULL,
    id_cliente INT NULL,
    total DECIMAL(10,2) NOT NULL DEFAULT 0,
    FOREIGN KEY (id_cajero) REFERENCES usuario(id),
    FOREIGN KEY (id_cliente) REFERENCES usuario(id)
);

CREATE TABLE detalle_venta (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);
GO

INSERT INTO usuario (username, clave, nombre, rol) VALUES
('admin',    'admin123', 'Administrador General', 'ADMIN'),
('super',    'super123', 'Supervisor de Tienda',  'SUPERVISOR'),
('caja1',    'caja123',  'Cajero Uno',            'CAJERO'),
('cliente1', 'cli123',   'Cliente de Prueba',     'CLIENTE');

INSERT INTO producto (codigo, nombre, categoria, precio, stock) VALUES
('P001', 'Arroz 1kg',       'Abarrotes', 4.50, 100),
('P002', 'Aceite 1L',       'Abarrotes', 9.90, 60),
('P003', 'Leche 400g',      'Lácteos',   4.20, 80),
('P004', 'Galletas Soda',   'Snacks',    1.50, 200),
('P005', 'Gaseosa 500ml',   'Bebidas',   2.50, 150),
('P006', 'Agua 625ml',      'Bebidas',   1.50, 150),
('P007', 'Fideos 500g',     'Abarrotes', 3.20, 90),
('P008', 'Detergente 500g', 'Limpieza',  6.80, 40);
GO
