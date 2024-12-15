# game-of-life

An implementation of Conway's Game of Life in Clojure with a user interface made with cljfx

## Installation and Usage

### Download the jar file directly

Download the jar file from https://github.com/FranciscoZizzi/game-of-life/releases/download/v1.0.0/game-of-life-1.0.0-standalone.jar

Then execute it with:

    $ java -jar game-of-life-1.0.0.jar

### Clone the repo and compile it yourself

#### Requirements
1. Java
2. Clojure
3. Leiningen

Clone the repository and run:

    $ lein uberjar

Then execute the jar file:

    $ java -jar target/uberjar/game-of-life-1.0.0-standalone.jar

## License

Copyright © 2024 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
