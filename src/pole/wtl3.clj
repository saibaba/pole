; wtl = walk the line, LYAH
(ns pole.wtl3)

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
