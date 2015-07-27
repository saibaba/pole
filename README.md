# pole

A Clojure library to play with Pole Monad example in LYAH

## Usage

See tests.

* wtl1 -> plain old way of coding with no monads; you can see control flow handling is not great
* wtl2 -> fixes flow control (i.e., preventing landing on an unbalanced pole) issue; see how the check for unbalanced pole (nil condition) is duplicated in each function in the chain (land-left and land-right)
* wtl3 -> Uses monad (just-pole, nothing-pole combo) to extract the generic, repetitive check for unbalanced pole from each of the chaining functions (land-left, land-right).

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
