

# Ejercicio de análisis exploratorio de datos

Haz una copia de la carpeta "cars" a tu area de ensayo.

Vamos a realizar un análisis exploratorio de los datos contenido en el fichero Electric_Vehicle_Population_Data.csv.

El metadata del fichero es:

|Columna | Descripcion|
|----------|-----------|
|VIN (1-10)| Partial vehicle identification number consisting of the first 10 digits.|
|County| The county where the vehicle is registered.|
|City| The city where the vehicle is registered.|
|State| The state where the vehicle is registered.|
|Postal Code| The postal code of the vehicle registration location|
|Model Year| The year the vehicle was manufactured.|
|Make| The manufacturer or brand of the vehicle.|
|Model| The specific model of the vehicle.|
|Electric Vehicle Type| Indicates whether the vehicle is a Battery Electric Vehicle (BEV) or a Plug-in Hybrid Electric Vehicle (PHEV).|
|Clean Alternative Fuel Vehicle (CAFV) Eligibility| Indicates if the vehicle is eligible for Clean Alternative Fuel Vehicle benefits.|
|Electric Range| The range of the vehicle on a full electric charge.|
|Base MSRP| The manufacturer's suggested retail price for the vehicle.|
|Legislative District| The legislative district associated with the vehicle registration location.|
|DOL Vehicle ID| Unique identifier assigned by the Washington State Department of Licensing.|
|Vehicle Location| The precise location of the vehicle.|
|Electric Utility| The electric utility company associated with the vehicle.|
|2020 Census Tract| The census tract where the vehicle is registered.|

## ¿Cuantos registros hay en el fichero?

```bash
awk -F';' 'END {print NR}' Electric_Vehicle_Population_Data.csv
or
wc
```
> Output: 99785

## ¿De cuantos estados hay vehículos registrados?
```bash
cut -d';' -f4 Electric_Vehicle_Population_Data.csv | sort | uniq | grep -v State | wc -l
```
> Output: 5

## En que posición se encuentra la columna con el año de fabricación
```bash
head -1 Electric_Vehicle_Population_Data.csv | sed 's/;/\n/g' | nl | grep "Model Year"
```
> Output: 6 Model Year

## En que año se fabricó el vehículo matriculado en Texas (TX)
```bash
cut -d';' -f4,6 Electric_Vehicle_Population_Data.csv | sort | uniq | grep -v State | grep TX | sed 's/;/\n/g' | tail -1
```
> Output: 2019

## Cual es el modelo de vehículo matriculado en Californía (CA)
```bash
cut -d';' -f4,8 Electric_Vehicle_Population_Data.csv | grep ^CA | sed 's/;/\n/g' | grep -v CA
```
> Output: MODEL Y

## De cuantas ciudades del estado de Washigthon hay datos en el fichero
```bash
cut -d';' -f4,3 Electric_Vehicle_Population_Data.csv | grep WA | cut -d';' -f1 | tr '[:upper:]' '[:lower:]' | tr '-' ' ' | sort | uniq | wc -l
```
> Output: 349

## De los vehículos registrados en la ciudad de Shelton, el que tiene el mayor rango electrico, cuantas millas puede recorrer?
```bash
cut -d';' -f3,11 Electric_Vehicle_Population_Data.csv | grep Shelton | sort -t';' -k2 -nr | head -1 | sed 's/;/\n/g' | grep -v Shelton
```
> Output: 330

## Cual es el DOL vehicle ID de ese vehículo que alcanza esa distancia máxima?
```bash
cut -d';' -f3,11,14 Electric_Vehicle_Population_Data.csv | grep Shelton | sort -t';' -k2 -nr | head -1 | sed 's/;/\n/g' | tail -1
```
> Output: 108257964

## Cuales son los fabricantes que tienen más de 4000 vehiculos registrados
```bash

```
> Output: 