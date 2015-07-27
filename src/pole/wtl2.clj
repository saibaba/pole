; wtl = walk the line, LYAH
(ns pole.wtl2)

(defn pole-return        ; for monad return
  [l r]
  (fn [] (list l r)))

(defn land-left
  [c p]
  (if (not (nil? p))
    (let [ lr (apply p []) nl (+ (first lr) c) nr (last lr) d (Math/abs (- nl nr)) ]
      (if (< d 4)
        (pole-return nl nr)
        nil))
    nil))

(defn land-right
  [c p]
  (if (not (nil? p))
    (let [ lr (apply p []) nl (first lr) nr (+ (last lr) c) d (Math/abs (- nl nr)) ]
      (if (< d 4)
        (pole-return nl nr)
        nil))
    nil))
