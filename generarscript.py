import sys

def generar_script():
    # Solicitar nombre de la tabla
    tabla = input("Nombre de la tabla: ")

    # Solicitar columnas y tipos de datos
    columnas = input("Columnas (separadas por comas): ").split(',')
    tipos_datos = input("Tipos de datos (separados por comas): ").split(',')

    # Verificar si la cantidad de columnas y tipos de datos es la misma
    if len(columnas) != len(tipos_datos):
        print("Error: La cantidad de columnas y tipos de datos debe ser la misma.")
        return

    # Generar sentencia CREATE TABLE
    create_table = f"CREATE TABLE {tabla} ("
    for i in range(len(columnas)):
        create_table += f"{columnas[i].strip()} {tipos_datos[i].strip()}, "
    create_table = create_table.rstrip(', ') + ");"

    print("\nSentencia CREATE TABLE generada:\n")
    print(create_table)

    # Solicitar datos para inserción
    datos = []
    while True:
        fila = input("\nIngrese datos para una fila (o escriba 'fin' para terminar): ")
        if fila.lower() == 'fin':
            break
        datos.append(tuple(fila.split(',')))

    # Generar sentencias INSERT
    insert_statements = f"\n-- Sentencias INSERT\n"
    for dato in datos:
        insert_statements += f"INSERT INTO {tabla} ({', '.join(columnas)}) VALUES {dato};\n"

    print(insert_statements)

if __name__ == "__main__":
    generar_script()
