# lift_records

A simple tool to keep track of how much weight you used given the exercise, the number of sets and the number of reps.

The "database" is just a simple JSON file located in ~/.lift_records/db.json

## Usage

    $ java -jar lift_records-0.1.0-standalone.jar [args]

args must be composed in one of the following manners:
- add "exercise name" <number_of_set>x<number_of_reps> weight. The tool adds a new record to the exercise. Also if weight is greater than the last max weight recorded the tools updates set a new max weight.
- max "exercise name" <number_of_set>x<number_of_reps>. The tool shows in the terminal the current max weight recorded for the exercise, sets and reps.
- story "exercise name" <number_of_set>x<number_of_reps>. The tool shows in the terminal all the record stored in the db for the exercise, sets and reps.

## License

Copyright © 2015 Thomas Introini

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
