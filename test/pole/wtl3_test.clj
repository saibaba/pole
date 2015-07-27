(ns pole.wtl3-test
  (:require [clojure.test :refer :all]
            [pole.wtl3 :refer :all]))

(deftest t-test1
  (testing "test1"
    (let [ pm ( ( (pole-return 0 0)  (partial land-left 1) ) (partial land-right 3) )
           p (pm pole-writer) l (first p) r (last p) ]
      (is (= l 1))
      (is (= r 3)))))

(deftest t-test-bug-fixed
  (testing "test with bug-fixed"
    (let [ pm ( ( ( ( (pole-return 0 0) (partial land-left 1) ) (partial land-right 4)) (partial land-left -1)) (partial land-right -2))
           p (pm pole-writer) ]
      (is (= nothing-pole p)))))
