(fn [fst snd]
   (loop [resu '()
          ifst fst
          isnd snd]
     (let [i (first ifst)
           j (first isnd)]
       (if (and i j)
         (recur (concat resu [i j])
                (rest ifst)
                (rest isnd))
         resu))))