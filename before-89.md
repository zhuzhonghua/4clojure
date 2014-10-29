[http://www.4clojure.com/problem/30](http://www.4clojure.com/problem/30)


    (fn compr [s]
      (if (< (count s) 2)
          [(first s)]
        (if (not= (first s) (first (rest s)))
            (reduce conj [(first s)] (compr (rest s)))
          (compr (rest s)))))

---
[http://www.4clojure.com/problem/28](http://www.4clojure.com/problem/28)

    (fn flat [seq]
      (if (sequential? seq)
          (if (> (count seq) 0)
              (reduce conj (flat (first seq)) (flat (rest seq)))
            seq)
        [seq]))

---
[http://www.4clojure.com/problem/29](http://www.4clojure.com/problem/29)

    (fn ups [s]
      (if (> (count s) 0)
          (let [c (first s)]
            (if (and (= (str c) (clojure.string/capitalize c))
                     (Character/isLetter c))
                (str c (ups (subs s 1)))
              (ups (subs s 1))))
        (str "")))

---
[http://www.4clojure.com/problem/26](http://www.4clojure.com/problem/26)

    (fn [n] (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))
    
---
[http://www.4clojure.com/problem/27](http://www.4clojure.com/problem/27)

    (fn [seq]
        ((fn palindrome[n1 n2]
            (if (< n1 n2)
                (if (= (nth seq n1)
                       (nth seq n2))
                    (palindrome (inc n1) (dec n2))
                  false)
              true)) 0 (dec (count seq))))
              
---
[http://www.4clojure.com/problem/23](http://www.4clojure.com/problem/23)

    #(reduce conj () %)
    
---

[http://www.4clojure.com/problem/25](http://www.4clojure.com/problem/25)

    #(filter (fn [x] (not= 0 (mod x 2))) %)
    
---
[http://www.4clojure.com/problem/24](http://www.4clojure.com/problem/24)

    #(reduce + %)
    
---
[http://www.4clojure.com/problem/22](http://www.4clojure.com/problem/22)

    (fn cc [x] 
      (if (next x) 
        (+ 1 (cc (rest x)))
        1))

---
[http://www.4clojure.com/problem/21](http://www.4clojure.com/problem/21)

    (fn [x n] (if (= 0 n) (first x) (recur (next x) (- n 1))))
    
---
[http://www.4clojure.com/problem/20](http://www.4clojure.com/problem/20)

    (fn [x] (if ( = 2 (count x)) (first x) (recur (next x))))
    
---
[http://www.4clojure.com/problem/19](http://www.4clojure.com/problem/19)

    (fn recu [x] (if (next x) (recu (next x)) (first x)))
    
---
[http://www.4clojure.com/problem/38](http://www.4clojure.com/problem/38)

    (fn [k & seq]
      (reduce (fn [x y] (if (> x y) x y)) k seq))

---
[http://www.4clojure.com/problem/65](http://www.4clojure.com/problem/65)
    
    (fn mm [q]
      (if (= (count q) (count (conj q nil)))
        :map
        (let [q2 (conj q 1 1)
              sub (- (count q2) (count q))]
          (if (= 1 sub)
            :set
            (let [q3 (conj q :my :my2)]
              (if (= (first q3) :my2)
                :list
                :vector))))))
                
---
[http://www.4clojure.com/problem/58](http://www.4clojure.com/problem/58)

    (fn mm [& fs]
      (let [fs (reverse fs)]
        (fn [& args]
          (loop [ret (apply (first fs) args)
                 fs (next fs)]
            (if (first fs)
              (recur ((first fs) ret) (next fs))
              ret)))))
              
这里最关键的一点要知道，返回的fn中的参数，使用时需要apply，后续再次递归调用时，就不需要apply了

再有就是学习使用了loop和recur的结合用法

---
[http://www.4clojure.com/problem/59](http://www.4clojure.com/problem/59)

    (fn mm [& fns]
      (fn [& args]
        (reduce #(conj %1 (apply %2 args)) [] fns)))
        
本题的关键在于要学会结合使用reduce 和conj        

---
[http://www.4clojure.com/problem/60](http://www.4clojure.com/problem/60)

    (fn mm
      ([f col] (mm f (first col) (rest col)))
      ([f init col] (cons init 
                          (lazy-seq 
                             (when-let [s (seq col)]
                               (mm f (f init (first col)) (rest col)))))))
                               
本题主要学习了如何使用cons + lazy-seq和递归，来构造惰性序列

---
[http://www.4clojure.com/problem/61](http://www.4clojure.com/problem/61)

    (fn mm [key val]
      (apply merge (map #(assoc nil %1 %2) key val)))
      
本题的关键在于结合使用merge和map

---
[http://www.4clojure.com/problem/62](http://www.4clojure.com/problem/62)
    
    (fn mm [f x]
      (cons x
            (lazy-seq (mm f (f x)))))
            
本题跟60题的思路一样            

---
[http://www.4clojure.com/problem/63](http://www.4clojure.com/problem/63)

    (fn mm[f arg]
      (reduce (fn [ret x]
                (let [k (f x)]
                  (assoc ret k (conj (get ret k []) x)))) 
              {} 
              arg))

本题主要学习了get的使用以及在希望累计结果时，使用reduce              
---
[http://www.4clojure.com/problem/66](http://www.4clojure.com/problem/66)

    (fn mm [m n]
      (loop [div (min m n)]
        (if (and (= 0 (mod m div)) 
                 (= 0 (mod n div)))
          div
          (recur (dec div)))))

本题主要学习了loop的用法          

---
[http://www.4clojure.com/problem/67](http://www.4clojure.com/problem/67)

    (fn mm [n]
      (take n (iterate (fn [x]
                         (first (filter (fn [y] 
                                          (nil? (some #(zero? (mod y %)) (range 2 y)))) 
                                        (iterate inc (inc x)))))
                       2)))
                       
本题主要学习了 some的使用，初步涉及到自底向上的方法

---
[http://www.4clojure.com/problem/69](http://www.4clojure.com/problem/69)

    (fn mm [f m1 & args]
      (reduce (fn [ret arg]
                (reduce (fn [ret2 ar]
                          (let [k (key ar)
                                v (val ar)]
                            (if (ret2 k)
                              (assoc ret2 k (f (ret2 k) v))
                              (merge ret2 ar))))
                        ret
                        arg))
              m1 
              args))
              
本题更深一步体会到自底向上的思想，代码块一点一点积累起来              
[http://www.4clojure.com/problem/70](http://www.4clojure.com/problem/70)
    
    (fn mm [arg]
      (sort-by #(.toLowerCase %) (re-seq #"\w+" arg)))
      
本题学习了sort-by配合.toLowerCase方法的使用
---
[http://www.4clojure.com/problem/73](http://www.4clojure.com/problem/73)

    (fn mm [tt]
      (let [x (nth tt 0)
            y (nth tt 1)
            z (nth tt 2)]
        (cond
         (and (not= :e (nth x 0)) (= (nth x 0) (nth x 1) (nth x 2))) (nth x 0)
         (and (not= :e (nth y 0)) (= (nth y 0) (nth y 1) (nth y 2))) (nth y 0)
         (and (not= :e (nth z 0)) (= (nth z 0) (nth z 1) (nth z 2))) (nth z 0)
         (and (not= :e (nth x 0)) (= (nth x 0) (nth y 0) (nth z 0))) (nth x 0)
         (and (not= :e (nth x 1)) (= (nth x 1) (nth y 1) (nth z 1))) (nth x 1)
         (and (not= :e (nth x 2)) (= (nth x 2) (nth y 2) (nth z 2))) (nth x 2)
         (and (not= :e (nth x 0)) (= (nth x 0) (nth y 1) (nth z 2))) (nth x 0)
         (and (not= :e (nth x 2)) (= (nth x 2) (nth y 1) (nth z 0))) (nth x 2)
         :else nil))) 
         
---
[http://www.4clojure.com/problem/74](http://www.4clojure.com/problem/74)

    (fn mm [arg]
      (->> (re-seq #"\d+" arg)
           (map #(Integer. %))
           (filter (fn [n]
                     (let [sq (int (Math/sqrt n))]
                       (= n (* sq sq)))))
           (clojure.string/join ",")))
           
---
[http://www.4clojure.com/problem/75](http://www.4clojure.com/problem/75)
    
    (fn mm [n]
      (if (= n 1)
        1
        (int (apply * (conj (map #(- 1 (/ 1 %)) 
                                  (filter #(not (some (fn [k] (= 0 (mod % k))) (range 2 %)))
                                          (filter #(= 0 (mod n %))
                                                  (range 2 n))))
                             n)))))
                             
---
[http://www.4clojure.com/problem/77](http://www.4clojure.com/problem/77)
    
    (fn mm2 [lst]
      (->> lst
           (map (fn [item] 
                  {(set (seq item)) [item]}))
           (cons concat)
           (apply merge-with)
           vals
           (map set)
           (filter #(> (count %) 1))
           set))
        
本题主要学习了merge-with的使用，以及->>让程序看起来更直观

---
[http://www.4clojure.com/problem/79](http://www.4clojure.com/problem/79)
    
    (fn mm2 [rows]
      (apply (fn mm [res n rows]
              (if-let [row (first rows)]
                (let [res1 (mm (+ res (row n)) n (rest rows))
                      res2 (mm (+ res (row (inc n))) (inc n) (rest rows))]
                  (if (< res1 res2)
                    res1
                    res2))
                res)) [((first rows) 0) 0 (rest rows)]))
                
---
[http://www.4clojure.com/problem/80](http://www.4clojure.com/problem/80)

    (fn [n]
    	(= n (apply + (filter #(= 0 (mod n %)) (range 1 n)))))
    	
---
[http://www.4clojure.com/problem/81](http://www.4clojure.com/problem/81)

    (fn mm [a b]
      (set (filter #(b %) a)))
      
---
[http://www.4clojure.com/problem/82](http://www.4clojure.com/problem/82)

    (fn mm [all]
      (letfn [(diff [a b]
                  (let [a-len (count a)
                        b-len (count b)]
                    (cond 
                      (= a-len b-len)       (= a-len (+ 1 (count (filter (fn [x] (= true x)) (map #(= %1 %2) a b)))))
                      (= (+ 1 a-len) b-len) (every? #((set b) %) (set a))
                      (= (+ 1 b-len) a-len) (every? #((set a) %) (set b))
                      :else nil)))
            (throu [a path]
                    ;(println a path)
                    (let [all-len (count all)
                          path-len (count path)]
                      (if (= all-len path-len)
                        true
                        (let [chid (filter #(and (not (path %)) (diff a %)) all)]
                          ;(println a chid)
                          (some (fn [c]
                                  (throu c (conj path c))) chid)))))]
        (not (not (some #(throu % #{%}) all)))))
        
---
[http://www.4clojure.com/problem/83](http://www.4clojure.com/problem/83)

    (fn mm[& args]
    	(true? (and (some true? args) (not (every? true? args)))))

---
[http://www.4clojure.com/problem/84](http://www.4clojure.com/problem/84)
    
    (fn mm [all]
      (let [all-map (into {} (seq all))
            link (reduce (fn [rset key]
                           (if-let [nex (all-map (key 1))]
                              (loop [r (conj key nex)
                                     ne nex]
                                (if-let [nex (all-map ne)]
                                  (recur (conj r nex) nex)
                                  (conj rset r)))
                              (conj rset key)))
                         #{} all)]
        ;(println link)
        (set (mapcat seq (mapcat (fn [one]
                              (loop [result []
                                     src one]
                                (if (= 1 (count src))
                                  result
                                  (recur (conj result (seq (map #(vector (first src) %)
                                                                (rest src))))
                                         (rest src)))))
                            link)))))
                            
参考[http://www.oschina.net/question/116260_78078](http://www.oschina.net/question/116260_78078)

---
[http://www.4clojure.com/problem/85](http://www.4clojure.com/problem/85)

    (fn mm [s]
      (if (= nil (first s))
        #{#{}}
        (let [sub-s (mm (rest s))
              fst-s (first s)
              sup-s (set (map #(conj % fst-s) sub-s))]
          (set (concat sub-s sup-s)))))
          
参考[https://github.com/khotyn/4clojure-answer/blob/master/85-power-set.clj](https://github.com/khotyn/4clojure-answer/blob/master/85-power-set.clj)          

---
[http://www.4clojure.com/problem/86](http://www.4clojure.com/problem/86)
    
    (fn mm [n]
      (not= nil (some #(= 1 %) (take 10
                        (iterate (fn [x]
                                   (loop [sum 0
                                          t x]
                                     (if (= 0 t)
                                       sum
                                       (let [mod-x (int (mod t 10))
                                             sub-x (int (/ t 10))]
                                         (recur (+ sum (* mod-x mod-x)) sub-x))))) n)))))
                                         
参考[https://github.com/khotyn/4clojure-answer/blob/master/86-happy-numbers.clj](https://github.com/khotyn/4clojure-answer/blob/master/86-happy-numbers.clj)得知，只要中间结果重复了，那么就可以认定失败                                         