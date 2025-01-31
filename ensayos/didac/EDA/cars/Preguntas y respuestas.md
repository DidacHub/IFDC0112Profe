

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
|DOL Ve1hicle ID| Unique identifier assigned by the Washington State Department of Licensing.|
|Vehicle Location| The precise location of the vehicle.|
|Electric Utility| The electric utility company associated with the vehicle.|
|2020 Census Tract| The census tract where the vehicle is registered.|

## ¿Cuántos registros hay en el fichero?

99785 registros
```bash
wc -l Electric_Vehicle_Population_Data.csv
```

## ¿De cuántos estados hay vehículos registrados?

6
```bash
cut -d ';' -f 4 Electric_Vehicle_Population_Data.csv | grep -v 'State' | sort | uniq | wc -l
```
## ¿En que posición se encuentra la columna con el año de fabricación?

```bash
Escribe la linea de comandos bash con la  que has obtenido la respuesta
```
## ¿En que año se fabricó el vehículo matriculado en Texas (TX)?

2019 
```bash
awk -F ';' '$4 == "TX" {print $6}' Electric_Vehicle_Population_Data.csv
```
## ¿Cuál es el modelo de vehículo matriculado en Californía (CA)?

MODEL Y
```bash
awk  -F ';' '/;CA;/ {print $8}' Electric_Vehicle_Population_Data.csv
```
## ¿De cuántas ciudades del estado de Washigthon hay datos en el fichero?
351
```bash
awk -F ';' '$4 == "WA" {cities[$3]} END {print length(cities)}' Electric_Vehicle_Population_Data.csv
```
## De los vehículos registrados en la ciudad de Shelton, el que tiene el mayor rango electrico, ¿cuántas millas puede recorrer?
330
```bash
awk -F ';' '$3 == "Shelton" && $11 > max {max = $11} END {print max}' Electric_Vehicle_Population_Data.csv
```
## ¿Cuál es el DOL vehicle ID de ese vehículo que alcanza esa distancia máxima?
108257964
```bash
awk -F ';' '$3 == "Shelton" && $11 > max {max = $11; value = $14} END {print value}' Electric_Vehicle_Population_Data.csv
```
## ¿Cuáles son los fabricantes que tienen más de 4000 vehiculos registrados?
NISSAN 6921
FORD 4398
KIA 4745
TESLA 43287
CHEVROLET 6790
BMW 4357
```bash
awk -F ';' '{count[$7]++} END {for (brand in count) if (count[brand] > 4000) print brand, count[brand]}' Electric_Vehicle_Population_Data.csv
```

## ¿Qué modelo de Nissa es lider en ventas?
LEAF
```bash
awk -F ';' '$7 == "NISSAN" {count[$7, $8]++} END {for (key in count) {split(key, arr, SUBSEP); if (count[key] > max) {max = count[key]; model = arr[2]}}} END {print model}' Elect
ric_Vehicle_Population_Data.csv
```

## Ordena de mayor a menor autonomía promedio a los fabricantes

240.18 TESLA
234 JAGUAR
233 POLESTAR
148.723 CHEVROLET
109.152 VOLKSWAGEN
105.593 NISSAN
100 TH!NK
100 WHEEGO ELECTRIC CARS
93.5744 PORSCHE
86.6244 KIA
85.7554 HYUNDAI
85.6567 FIAT
75.8108 AUDI
62.0982 SMART
56 AZURE DYNAMICS
47.6765 MINI
46.9489 BMW
46.5921 HONDA
41.0465 LAND ROVER
38.5858 MERCEDES-BENZ
37.1333 CADILLAC
36.9107 LEXUS
33 ALFA ROMEO
33 FISKER
32.2764 TOYOTA
32.1104 CHRYSLER
32 DODGE
31.9857 MITSUBISHI
26.2703 VOLVO
26 MAZDA
25.8891 FORD
24.2544 LINCOLN
22.2282 JEEP
17 SUBARU
0 Make

```bash
awk -F ';' '$11 > 0 {sum[$7] += $11; count[$7]++} END {for (brand in sum) avg[brand] = sum[brand] / count[brand]; for (brand in avg) print avg[brand], brand}' Electric_Vehicle_Population_Data.csv | sort -k1,1nr
```