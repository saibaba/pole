(ns pole.wtl2-test
  (:require [clojure.test :refer :all]
            [pole.wtl2 :refer :all]))

(deftest t-test1
  (testing "test1"
    (let [ p (land-left 2 (land-right 1 (land-left 1 (pole-return 0 0)))) r (apply p []) l (first r) r (last r) ]
      (is (= l 3))
      (is (= r 1)))))

(deftest t-test-bug-fixed
  (testing "test with bug-fixed"
    (let [ p (land-right -2 (land-left -1 (land-right 4 (land-left 1 (pole-return 0 0))))) ]
      (is (nil? p)))))
