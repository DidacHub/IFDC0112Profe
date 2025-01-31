#\!/usr/bin/bash
cut -d';' -f4 Electric_Vehicle_Population_Data.csv | sort | uniq | grep -v State | wc -l
