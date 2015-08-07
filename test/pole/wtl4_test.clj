(ns pole.wtl4-test
  (:require [clojure.test :refer :all]
            [pole.wtl4 :refer :all]))

(defn l1r3 [l r]
  (>>= (pole-return l r) (fn [p] 
  (>>= (land-left 1 p)
  (partial land-right 3)))))

(defn l1r1l2r2 [l r]
  (>>= (pole-return l r) (fn [p1] 
  (>>= (land-left 1 p1)  (fn [p2]
  (>>= (land-right 1 p2) (fn [p3]
  (>>= (land-left 1 p3)  (fn [p4]
  (>>= (land-right 1 p4) (fn [p5]
  (pole-return (first p5) (last p5)))))))))))))



(deftest t-test1
  (testing "test1"
    (let [ r (l1r3 0 0) p (r (fn [x] x)) lx (first p) rx (last p) ]
      (is (= lx 1))
      (is (= rx 3)))))

(deftest t-test2
  (testing "test2"
    (let [ r (l1r1l2r2 0 0) p (r (fn [x] x)) lx (first p) rx (last p) ]
      (is (= lx 2))
      (is (= rx 2)))))

(defn macro-l3
  [p]
  (do*
    (p1 <- (land-left 1 p))
    (p2 <- (land-left 1 p1))
    (p3 <- (land-left 1 p2))
    (pole-return (first p3) (last p3))))

(deftest t-do
  (testing "do macro"
    (let [p (pole 0 0) v (macro-l3 p) pr (v (fn [x] x)) l (first pr) r (last pr)  ]
      (is (= l 3))
      (is (= r 0)))))

(deftest t-assoc
  (testing "associativity"
    (let [e (partial land-left 1)
          f (partial land-right 2) 
          g (partial land-left 3)
          hra (>=> g (>=> f e))  ; i.e., g >=> (f >=> e)
          p (pole 0 0)
          r1 (hra p)
          r (r1 (fn [x] x))
          pl (first r)
          pr (last r)

          hla (>=> (>=> g f) e)   ; i.e., (g >=> f) >=> e
          lr1 (hla p)
          lr (lr1 (fn [x] x))
          lpl (first lr)
          lpr (last lr) ]
      (is (= pl 4))
      (is (= pr 2))
      (is (= lpl 4))
      (is (= lpr 2)))))
