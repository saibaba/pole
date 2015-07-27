; wtl = walk the line, LYAH
(ns pole.wtl1)

(defn pole-return        ; for monad return
  [l r]
  (fn [] (list l r)))

(defn land-left
  [c p]
  (let [ lr (apply p []) nl (+ (first lr) c) nr (last lr) ]
    (pole-return nl nr)))

(defn land-right
  [c p]
  (let [ lr (apply p []) nl (first lr) nr (+ (last lr) c) ]
    (pole-return nl nr)))
