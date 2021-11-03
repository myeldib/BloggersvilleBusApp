# BloggersvilleBusApp
Bloggersville needs a new application to process a joint timetable, and produce two modified timetables for each of its bus service company in order to optimize journeys from the airport to the central bus stop.

## Input format
The joint timetable input file has the following format:
```bash
<service>
<service> ...
<service><EOF>
```
Each <service> record is on a separate line and will consist of:
- The character string ‘Grotty’ or ‘Posh’ to indicate which company is running the service.
- A space
- The departure time in 24 hours format, represented by 5 characters as ‘HH:MM’
- A space
- The arrival time in 24 hours format, represented by 5 characters as ‘HH:MM’

Example of a service:
```bash
Posh 10:15 11:10
```

## Usage
The project has been developed using Java, and Maven. 
The project has been tested using Java version 1.8.0, and Maven version 5.10.16

- Clone this repository:
```bash
git clone git@github.com:myeldib/BloggersvilleBusApp.git
cd BloggersvilleBusApp
```
- Build:
```bash
mvn package
```

- Run:
```bash
java -jar target/BloggersvilleBusApp-0.0.1-SNAPSHOT-jar-with-dependencies.jar <path_to_input>
```

## Output format
The tool will generate a new file "modified_table.txt" based on the directory of the input file:
```bash
../BloggersvilleBusApp/input.txt
../BloggersvilleBusApp/modified_table.txt
```

The output timetables have the same format as the input timetable, with the Posh Bus Company
timetable first followed by a blank line and the Grotty Bus Company timetable:
```bash
<posh_service>
<posh_service> ...
<posh_service>
<blank_line>
<grotty_service>
<grotty_service> ...
<grotty_service><EOF>
```
Example input and output
Given the following data:
```bash
Posh 10:15 11:10
Posh 10:10 11:00
Grotty 10:10 11:00
Grotty 16:30 18:45
Posh 12:05 12:30
Grotty 12:30 13:25
Grotty 12:45 13:25
Posh 17:25 18:01
```
Your program shall produce:
```bash
Posh 10:10 11:00
Posh 10:15 11:10
Posh 12:05 12:30
Posh 17:25 18:01

Grotty 12:45 13:25
-EOF-
```
