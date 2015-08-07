; wtl = walk the line, LYAH
(ns pole.wtl4)

;; if not nul? check is so common, can we abstract the repetition in structure of land-left and land-right in wtl2 ?

(defn pole                ; just data type to store fields
  [l r]
  (list l r))

(defn just-pole           ; monad
  [x]
  (fn [m] (apply m [x])))

(defn nothing-pole
  [m]
  nothing-pole)

(defn nil-pole            ; in chaining, if the pole is unbalanced at any time, rest of the chaining is short circuited
  []
  nothing-pole)

(defn pole-return        ; for monad return
  [l r]
  (just-pole (pole l r)))

(defn pole-writer        ;; kind of unbind
  [p]
  (let [left (first p) right (last p)]
    (println "Left" left, ", Right" right))
  p)

(defn land-left
  [c p]
  (let [ nl (+ (first p) c) nr (last p) d (Math/abs (- nl nr)) ]
    (if (< d 4)
      (pole-return nl nr)
      (nil-pole))))

(defn land-right
  [c p]
  (let [ nl (first p) nr (+ (last p) c) d (Math/abs (- nl nr)) ]
    (if (< d 4)
      (pole-return nl nr)
      (nil-pole))))

; For monad bind, provide ability to compose land-left and land-right where the output of one is incompatible with the input to the other
; (  (pole-bind (partial land-left 2) (partial land-right 3))  just-pole)
;
; Actually, using function abstraction to represent Just (instead of defrecord, say) ruins the point of having this bind; 
; Since it is a function it can take a fn as argument directly without need to >>=.
; For exampe see in wlt3, test t-test-bug-fixed how function f is directly passed to just-pole)
(defn >>=
  [m f]
  (let [p (m (fn [p1] p1))]
    (if (not (nil? p))
      (f p)
      (nil-pole))))

; do itself

(defn reduce-form
  [result form]
    (let [v (first form) f (last form)]
      `(>>= ~f (fn [~v] ~result))))

(defmacro do*
  [& forms]
  (let [ rforms (reverse forms)]
    (reduce reduce-form rforms)))

(defn >=>
  [f g]
  (fn [p] (>>= (g p) f)))
