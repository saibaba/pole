(ns pole.wtl1-test
  (:require [clojure.test :refer :all]
            [pole.wtl1 :refer :all]))

(deftest t-test1
  (testing "test1"
    (let [ p (land-left 2 (land-right 1 (land-left 1 (pole-return 0 0)))) r (apply p []) l (first r) r (last r) ]
      (is (= l 3))
      (is (= r 1)))))

; From http://stackoverflow.com/questions/7852092/how-do-i-expect-failure-in-a-unit-test

(defmacro should-fail [body]
  `(let [report-type# (atom nil)]
     (binding [clojure.test/report #(reset! report-type# (:type %))]
       ~body)
     (testing "should fail"
       (is (= @report-type# :fail )))))

(deftest t-test-bug
  (testing "test with bug"
    (let [ p (land-right -2 (land-left -1 (land-right 4 (land-left 1 (pole-return 0 0))))) ]
      (should-fail (is (nil? p))))))
