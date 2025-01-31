# Diseño de un Servidor de Archivos para una Filmoteca Municipal

La ciudad de Terrassa ha digitalizado su filmoteca, la cual cuenta con aproximadamente 10,000 películas en formato MP4. Se estima que cada película tiene un tamaño promedio de [estimación del tamaño en GB]. La biblioteca municipal, a la que está asociada un 1,79% de los 222,000 habitantes de la ciudad, recibe aproximadamente 4 visitas por usuario al mes, con un promedio de una película vista por visita.

**Objetivo**: Diseñar un servidor de archivos capaz de almacenar y servir eficientemente esta colección de películas, considerando el crecimiento futuro de la colección y el aumento potencial en el número de usuarios.

## Tarea:

+ Investigación: Realizar una investigación exhaustiva sobre los diferentes tipos de procesadores, memorias y sistemas de almacenamiento disponibles en el mercado.

#### Procesadores

|Procesador | Precio Aproximado |Núcleos - Hilos|	Frecuencia (Base / Turbo) |	TDP |	Compatibilidad de socket|
|----------|----------------|---------------|---------------------------|---------|--------------------|
|Intel Core i9-13900K |	$600 USD |	24 (8 P-Core + 16 E-Core) |	3.0 GHz / 5.8 GHz |	125 W |	LGA 1700 |
|AMD Ryzen 9 7950X |	$700 USD |	16 / 32 | 4.5 GHz / 5.7 GH |	170 W |	AM5 |
|Intel Core i9-12900K |	$500 USD |	16 (8 P-Core + 8 E-Core) | 3.2 GHz / 5.2 GHz | 125 W | LGA 1700 |
|AMD Ryzen 9 5900X	| $550 USD | 12 / 24 | 3.7 GHz / 4.8 GHz | 105 W |	AM4 |
|Intel Core i7-13700K | $400 USD | 16 (8 P-Core + 8 E-Core) |	3.4 GHz / 5.4 GHz |	125 W |	LGA 1700 |
|AMD Ryzen 7 7800X3D | $500 USD | 8 / 16 |	4.2 GHz / 5.0 GHz |	120 W |	AM5 |
|Intel Core i5-13600K |	$300 USD | 14 (6 P-Core + 8 E-Core) | 3.5 GHz / 5.1 GHz | 125 W | LGA 1700 |
|AMD Ryzen 5 7600X | $300 USD | 6 / 12 | 4.7 GHz / 5.3 GHz | 105 W | AM5 |
|Intel Core i5-12600K | $270 USD |	10 (6 P-Core + 4 E-Core) |	3.7 GHz / 4.9 GHz |	125 W | LGA 1700 |
|AMD Ryzen 5 5600X | $230 USD |	6 / 12 | 3.7 GHz / 4.6 GHz | 65 W | AM4 |
|Intel Core i5-13400F |	$220 USD | 10 (6 P-Core + 4 E-Core) | 2.5 GHz / 4.6 GHz	| 65 W | LGA 1700 |
|AMD Ryzen 7 5700X | $350 USD |	8 / 16 | 3.4 GHz / 4.6 GHz |	65 W | AM4 |
|Intel Core i3-13100F |	$120 USD | 4 / 8 |3.4 GHz / 4.5 GHz	| 58 W | LGA 1700 |
|AMD Ryzen 3 4100 |	$100 USD | 4 / 4 | 3.8 GHz / 4.0 GHz | 65 W | AM4 |
|Intel Core i3-12100F | $110 USD | 4 / 8 | 3.3 GHz / 4.3 GHz | 58 W | LGA 1700 |
|AMD Ryzen 3 5300G | $130 USD | 4 / 8 | 3.9 GHz / 4.2 GHz |	65 W | AM4 |
|Intel Pentium Gold G7400 |	$65 USD | 2 / 4 | 3.7 GHz |	46 W | LGA 1700 |
|AMD Athlon 3000G |	$60 USD	| 2 / 4	| 3.4 GHz | 35 W | AM4 |

#### Memorias RAM

|Modelo | Capacidad | Frecuencia |	Latencia (CAS) | Tipo de memoria | Precio Aproximado |
|-------|-----------|-----------|-----------------|------------------|-------------------|
|Corsair Vengeance DDR5 6000MHz | 32 GB (2x16 GB) |	6000 MHz | CL36 | DDR5 | $200 USD |
|G.Skill Trident Z5 RGB DDR5 6400MHz | 32 GB (2x16 GB) | 6400 MHz |	CL32 | DDR5 | $250 USD |
|Kingston Fury Renegade DDR5 6400MHz |	32 GB (2x16 GB)	| 6400 MHz | CL32 |	DDR5 | $240 USD |
|Corsair Vengeance LPX DDR4 3200MHz | 16 GB  (2x8 GB) |	3200 MHz | CL16 | DDR4 | $60 USD |
|G.Skill Ripjaws V DDR4 3600MHz	| 16 GB (2x8 GB) | 3600 MHz | CL16 | DDR4 |	$75 USD |
|Crucial Ballistix DDR4 3200MHz	| 16 GB (2x8 GB) | 3200 MHz	| CL16 | DDR4 |	$55 USD |
|Corsair ValueSelect DDR4 2400MHz | 8 GB (1x8 GB) | 2400 MHz | CL16 | DDR4 | $30 USD |
|Kingston HyperX Fury DDR4 2666MHz | 8 GB (1x8 GB) | 2666 MHz | CL16 | DDR4	| $35 USD |
|Patriot Viper 4 DDR4 3000MHz | 8 GB (1x8 GB) | 3000 MHz | CL16 | DDR4 | $40 USD |

#### Memorias de almacenamiento

|Modelo | Gama | Capacidad | Interfaz | Velocidad de Rotación | Caché | Tipo de Uso |	Precio Aproximado|
|--------|----|----------|-------------|--------------------|--------|-------------|-------------------|
|Seagate Exos X20 20TB | Alta |	20 TB |	SATA 6Gb/s | 7200 RPM |	256 MB | Servidores, Centros de Datos |	$500 - $600 USD|
|Western Digital Gold 20TB | Alta | 20 TB |	SATA 6Gb/s | 7200 RPM |	256 MB | Centros de Datos, Almacenamiento empresarial | $550 - $650 USD|
|Toshiba MG08ACA20TE 20TB |	Alta | 20 TB | SATA 6Gb/s |	7200 RPM | 256 MB |	Servidores, Almacenamiento empresarial | $500 - $600 USD|
|Seagate Barracuda 20TB | Media | 20 TB	| SATA 6Gb/s | 5400 RPM | 256 MB | Almacenamiento personal, NAS |	$400 - $500 USD|
|Western Digital Red Plus 20TB | Media | 20 TB | SATA 6Gb/s | 5400 RPM | 256 MB	| NAS, Almacenamiento personal | $400 - $500 USD|
|Toshiba N300 20TB | Media | 20 TB | SATA 6Gb/s | 5400 RPM | 256 MB | NAS, Almacenamiento personal | $400 - $500 USD|
|Seagate Archive HDD 20TB | Baja | 20 TB | SATA 6Gb/s | 5900 RPM | 128 MB | Almacenamiento a largo plazo, Archivos | $300 - $400 USD|
|Western Digital Elements 20TB | Baja | 20 TB |	USB 3.0	| 5400 RPM | 128 MB | Almacenamiento personal, Backup | $300 - $400 USD|
|Toshiba Canvio 20TB | Baja | 20 TB | USB 3.0 | 5400 RPM | 128 MB |	Almacenamiento personal, Backup	| $300 - $400 USD|

+ Selección de componentes: Elegir los componentes más adecuados para el servidor, considerando factores como rendimiento, capacidad, costo y eficiencia energética.

|Intel Xeon W-2295 | $1500 USD | 18 / 36 |3 GHz / 4.6 GHz	| 165 W | LGA 2066 |
|Patriot Viper 4 DDR4 3000MHz | 8 GB (1x8 GB) | 3000 MHz | CL16 | DDR4 | $40 USD | x4
40000 gb 
|Toshiba N300 20TB | Media | 20 TB | SATA 6Gb/s | 5400 RPM | 256 MB | NAS, Almacenamiento personal | $400 - $500 USD| x2

+ Cálculo de requerimientos: Estimar la capacidad de almacenamiento total necesaria

La capacidad total para ser almacenada es de 40TB. Llegamos a esta conclusión, ya que si tenemos 10000 películas en formato mp4, probablemente en calidad 1080p nos da un peso medio de 4gb por película. Entonces multiplicamos 4 * 10000, obteniendo así 40000gb que convertidos son 40TB.

+ Elaboración de una presentación: Crear una presentación en PowerPoint que detalle: 
    + Justificación de la elección de los componentes: Explicar por qué se eligieron los componentes específicos y cómo satisfacen los requisitos del proyecto. 
    + Especificaciones técnicas: Detallar las características técnicas de cada componente (procesador, memoria, almacenamiento). 
    + Costo total: Estimar el costo de los componentes. 

En la presentación Powerpoint Incluir el logotimo de Foment de Terrasa.


### Estimación del tamaño de una película:

Para obtener una estimación más precisa del tamaño promedio de una película, puedes utilizar herramientas en línea o consultar las especificaciones técnicas de las películas que ya están digitalizadas. Sin embargo, como punto de partida, puedes estimar que una película de 120 minutos en calidad HD (1080p) puede ocupar entre 2GB y 4GB.