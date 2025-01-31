#\!/usr/bin/bash
awk -F";" '$3 == "Shelton"' Electric_Vehicle_Population_Data.csv | head -n 1 | cut -d';' -f14
