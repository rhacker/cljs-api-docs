## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/BitmapIndexedNode

 <table border="1">
<tr>
<td>type</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-1211"><img valign="middle" alt="[+] 0.0-1211" src="https://img.shields.io/badge/+-0.0--1211-lightgrey.svg"></a> </td>
</tr>
</table>

 <samp>
(__BitmapIndexedNode.__ edit bitmap arr)<br>
</samp>

```
(no docstring)
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:3583-3777](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L3583-L3777)</ins>
</pre>

```clj
(deftype BitmapIndexedNode [edit ^:mutable bitmap ^:mutable arr]
  Object
  (inode-assoc [inode shift hash key val added-leaf?]
    (let [bit (bitpos hash shift)
          idx (bitmap-indexed-node-index bitmap bit)]
      (if (zero? (bit-and bitmap bit))
        (let [n (bit-count bitmap)]
          (if (>= n 16)
            (let [nodes (make-array 32)
                  jdx   (mask hash shift)]
              (aset nodes jdx (.inode-assoc cljs.core.BitmapIndexedNode/EMPTY (+ shift 5) hash key val added-leaf?))
              (loop [i 0 j 0]
                (if (< i 32)
                  (if (zero? (bit-and (bit-shift-right-zero-fill bitmap i) 1))
                    (recur (inc i) j)
                    (do (aset nodes i
                              (if (coercive-not= nil (aget arr j))
                                (.inode-assoc cljs.core.BitmapIndexedNode/EMPTY
                                              (+ shift 5) (cljs.core/hash (aget arr j)) (aget arr j) (aget arr (inc j)) added-leaf?)
                                (aget arr (inc j))))
                        (recur (inc i) (+ j 2))))))
              (ArrayNode. nil (inc n) nodes))
            (let [new-arr (make-array (* 2 (inc n)))]
              (array-copy arr 0 new-arr 0 (* 2 idx))
              (aset new-arr (* 2 idx) key)
              (aset added-leaf? 0 true)
              (aset new-arr (inc (* 2 idx)) val)
              (array-copy arr (* 2 idx) new-arr (* 2 (inc idx)) (* 2 (- n idx)))
              (BitmapIndexedNode. nil (bit-or bitmap bit) new-arr))))
        (let [key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil)
                (let [n (.inode-assoc val-or-node (+ shift 5) hash key val added-leaf?)]
                  (if (identical? n val-or-node)
                    inode
                    (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) n))))

                (= key key-or-nil)
                (if (identical? val val-or-node)
                  inode
                  (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) val)))

                :else
                (do (aset added-leaf? 0 true)
                    (BitmapIndexedNode. nil bitmap
                                        (clone-and-set arr (* 2 idx) nil (inc (* 2 idx))
                                                       (create-node (+ shift 5) key-or-nil val-or-node hash key val)))))))))

  (inode-without [inode shift hash key]
    (let [bit (bitpos hash shift)]
      (if (zero? (bit-and bitmap bit))
        inode
        (let [idx         (bitmap-indexed-node-index bitmap bit)
              key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil)
                (let [n (.inode-without val-or-node (+ shift 5) hash key)]
                  (cond (identical? n val-or-node) inode
                        (coercive-not= nil n) (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) n))
                        (== bitmap bit) nil
                        :else (BitmapIndexedNode. nil (bit-xor bitmap bit) (remove-pair arr idx))))
                (= key key-or-nil)
                (BitmapIndexedNode. nil (bit-xor bitmap bit) (remove-pair arr idx))
                :else inode)))))

  (inode-find [inode shift hash key]
    (let [bit (bitpos hash shift)]
      (if (zero? (bit-and bitmap bit))
        nil
        (let [idx         (bitmap-indexed-node-index bitmap bit)
              key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil) (.inode-find val-or-node (+ shift 5) hash key)
                (= key key-or-nil)          [key-or-nil val-or-node]
                :else nil)))))

  (inode-find [inode shift hash key not-found]
    (let [bit (bitpos hash shift)]
      (if (zero? (bit-and bitmap bit))
        not-found
        (let [idx         (bitmap-indexed-node-index bitmap bit)
              key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil) (.inode-find val-or-node (+ shift 5) hash key not-found)
                (= key key-or-nil)          [key-or-nil val-or-node]
                :else not-found)))))

  (inode-seq [inode]
    (create-inode-seq arr))

  (ensure-editable [inode e]
    (if (identical? e edit)
      inode
      (let [n       (bit-count bitmap)
            new-arr (make-array (if (neg? n) 4 (* 2 (inc n))))]
        (array-copy arr 0 new-arr 0 (* 2 n))
        (BitmapIndexedNode. e bitmap new-arr))))

  (edit-and-remove-pair [inode e bit i]
    (if (== bitmap bit)
      nil
      (let [editable (.ensure-editable inode e)
            earr     (.-arr editable)
            len      (.-length earr)]
        (set! (.-bitmap editable) (bit-xor bit (.-bitmap editable)))
        (array-copy earr (* 2 (inc i))
                    earr (* 2 i)
                    (- len (* 2 (inc i))))
        (aset earr (- len 2) nil)
        (aset earr (dec len) nil)
        editable)))

  (inode-assoc! [inode edit shift hash key val added-leaf?]
    (let [bit (bitpos hash shift)
          idx (bitmap-indexed-node-index bitmap bit)]
      (if (zero? (bit-and bitmap bit))
        (let [n (bit-count bitmap)]
          (cond
            (< (* 2 n) (.-length arr))
            (let [editable (.ensure-editable inode edit)
                  earr     (.-arr editable)]
              (aset added-leaf? 0 true)
              (array-copy-downward earr (* 2 idx)
                                   earr (* 2 (inc idx))
                                   (* 2 (- n idx)))
              (aset earr (* 2 idx) key)
              (aset earr (inc (* 2 idx)) val)
              (set! (.-bitmap editable) (bit-or (.-bitmap editable) bit))
              editable)

            (>= n 16)
            (let [nodes (make-array 32)
                  jdx   (mask hash shift)]
              (aset nodes jdx (.inode-assoc! cljs.core.BitmapIndexedNode/EMPTY edit (+ shift 5) hash key val added-leaf?))
              (loop [i 0 j 0]
                (if (< i 32)
                  (if (zero? (bit-and (bit-shift-right-zero-fill bitmap i) 1))
                    (recur (inc i) j)
                    (do (aset nodes i
                              (if (coercive-not= nil (aget arr j))
                                (.inode-assoc! cljs.core.BitmapIndexedNode/EMPTY
                                               edit (+ shift 5) (cljs.core/hash (aget arr j)) (aget arr j) (aget arr (inc j)) added-leaf?)
                                (aget arr (inc j))))
                        (recur (inc i) (+ j 2))))))
              (ArrayNode. edit (inc n) nodes))

            :else
            (let [new-arr (make-array (* 2 (+ n 4)))]
              (array-copy arr 0 new-arr 0 (* 2 idx))
              (aset new-arr (* 2 idx) key)
              (aset added-leaf? 0 true)
              (aset new-arr (inc (* 2 idx)) val)
              (array-copy arr (* 2 idx) new-arr (* 2 (inc idx)) (* 2 (- n idx)))
              (let [editable (.ensure-editable inode edit)]
                (set! (.-arr editable) new-arr)
                (set! (.-bitmap editable) (bit-or (.-bitmap editable) bit))
                editable))))
        (let [key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil)
                (let [n (.inode-assoc! val-or-node edit (+ shift 5) hash key val added-leaf?)]
                  (if (identical? n val-or-node)
                    inode
                    (edit-and-set inode edit (inc (* 2 idx)) n)))

                (= key key-or-nil)
                (if (identical? val val-or-node)
                  inode
                  (edit-and-set inode edit (inc (* 2 idx)) val))

                :else
                (do (aset added-leaf? 0 true)
                    (edit-and-set inode edit (* 2 idx) nil (inc (* 2 idx))
                                  (create-node edit (+ shift 5) key-or-nil val-or-node hash key val))))))))

  (inode-without! [inode edit shift hash key removed-leaf?]
    (let [bit (bitpos hash shift)]
      (if (zero? (bit-and bitmap bit))
        inode
        (let [idx         (bitmap-indexed-node-index bitmap bit)
              key-or-nil  (aget arr (* 2 idx))
              val-or-node (aget arr (inc (* 2 idx)))]
          (cond (coercive-= nil key-or-nil)
                (let [n (.inode-without! val-or-node edit (+ shift 5) hash key removed-leaf?)]
                  (cond (identical? n val-or-node) inode
                        (coercive-not= nil n) (edit-and-set inode edit (inc (* 2 idx)) n)
                        (== bitmap bit) nil
                        :else (.edit-and-remove-pair inode edit bit idx)))
                (= key key-or-nil)
                (do (aset removed-leaf? 0 true)
                    (.edit-and-remove-pair inode edit bit idx))
                :else inode)))))

  (kv-reduce [inode f init]
    (inode-kv-reduce arr f init)))
```


---

```clj
{:full-name "cljs.core/BitmapIndexedNode",
 :ns "cljs.core",
 :name "BitmapIndexedNode",
 :type "type",
 :signature ["[edit bitmap arr]"],
 :source {:code "(deftype BitmapIndexedNode [edit ^:mutable bitmap ^:mutable arr]\n  Object\n  (inode-assoc [inode shift hash key val added-leaf?]\n    (let [bit (bitpos hash shift)\n          idx (bitmap-indexed-node-index bitmap bit)]\n      (if (zero? (bit-and bitmap bit))\n        (let [n (bit-count bitmap)]\n          (if (>= n 16)\n            (let [nodes (make-array 32)\n                  jdx   (mask hash shift)]\n              (aset nodes jdx (.inode-assoc cljs.core.BitmapIndexedNode/EMPTY (+ shift 5) hash key val added-leaf?))\n              (loop [i 0 j 0]\n                (if (< i 32)\n                  (if (zero? (bit-and (bit-shift-right-zero-fill bitmap i) 1))\n                    (recur (inc i) j)\n                    (do (aset nodes i\n                              (if (coercive-not= nil (aget arr j))\n                                (.inode-assoc cljs.core.BitmapIndexedNode/EMPTY\n                                              (+ shift 5) (cljs.core/hash (aget arr j)) (aget arr j) (aget arr (inc j)) added-leaf?)\n                                (aget arr (inc j))))\n                        (recur (inc i) (+ j 2))))))\n              (ArrayNode. nil (inc n) nodes))\n            (let [new-arr (make-array (* 2 (inc n)))]\n              (array-copy arr 0 new-arr 0 (* 2 idx))\n              (aset new-arr (* 2 idx) key)\n              (aset added-leaf? 0 true)\n              (aset new-arr (inc (* 2 idx)) val)\n              (array-copy arr (* 2 idx) new-arr (* 2 (inc idx)) (* 2 (- n idx)))\n              (BitmapIndexedNode. nil (bit-or bitmap bit) new-arr))))\n        (let [key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil)\n                (let [n (.inode-assoc val-or-node (+ shift 5) hash key val added-leaf?)]\n                  (if (identical? n val-or-node)\n                    inode\n                    (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) n))))\n\n                (= key key-or-nil)\n                (if (identical? val val-or-node)\n                  inode\n                  (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) val)))\n\n                :else\n                (do (aset added-leaf? 0 true)\n                    (BitmapIndexedNode. nil bitmap\n                                        (clone-and-set arr (* 2 idx) nil (inc (* 2 idx))\n                                                       (create-node (+ shift 5) key-or-nil val-or-node hash key val)))))))))\n\n  (inode-without [inode shift hash key]\n    (let [bit (bitpos hash shift)]\n      (if (zero? (bit-and bitmap bit))\n        inode\n        (let [idx         (bitmap-indexed-node-index bitmap bit)\n              key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil)\n                (let [n (.inode-without val-or-node (+ shift 5) hash key)]\n                  (cond (identical? n val-or-node) inode\n                        (coercive-not= nil n) (BitmapIndexedNode. nil bitmap (clone-and-set arr (inc (* 2 idx)) n))\n                        (== bitmap bit) nil\n                        :else (BitmapIndexedNode. nil (bit-xor bitmap bit) (remove-pair arr idx))))\n                (= key key-or-nil)\n                (BitmapIndexedNode. nil (bit-xor bitmap bit) (remove-pair arr idx))\n                :else inode)))))\n\n  (inode-find [inode shift hash key]\n    (let [bit (bitpos hash shift)]\n      (if (zero? (bit-and bitmap bit))\n        nil\n        (let [idx         (bitmap-indexed-node-index bitmap bit)\n              key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil) (.inode-find val-or-node (+ shift 5) hash key)\n                (= key key-or-nil)          [key-or-nil val-or-node]\n                :else nil)))))\n\n  (inode-find [inode shift hash key not-found]\n    (let [bit (bitpos hash shift)]\n      (if (zero? (bit-and bitmap bit))\n        not-found\n        (let [idx         (bitmap-indexed-node-index bitmap bit)\n              key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil) (.inode-find val-or-node (+ shift 5) hash key not-found)\n                (= key key-or-nil)          [key-or-nil val-or-node]\n                :else not-found)))))\n\n  (inode-seq [inode]\n    (create-inode-seq arr))\n\n  (ensure-editable [inode e]\n    (if (identical? e edit)\n      inode\n      (let [n       (bit-count bitmap)\n            new-arr (make-array (if (neg? n) 4 (* 2 (inc n))))]\n        (array-copy arr 0 new-arr 0 (* 2 n))\n        (BitmapIndexedNode. e bitmap new-arr))))\n\n  (edit-and-remove-pair [inode e bit i]\n    (if (== bitmap bit)\n      nil\n      (let [editable (.ensure-editable inode e)\n            earr     (.-arr editable)\n            len      (.-length earr)]\n        (set! (.-bitmap editable) (bit-xor bit (.-bitmap editable)))\n        (array-copy earr (* 2 (inc i))\n                    earr (* 2 i)\n                    (- len (* 2 (inc i))))\n        (aset earr (- len 2) nil)\n        (aset earr (dec len) nil)\n        editable)))\n\n  (inode-assoc! [inode edit shift hash key val added-leaf?]\n    (let [bit (bitpos hash shift)\n          idx (bitmap-indexed-node-index bitmap bit)]\n      (if (zero? (bit-and bitmap bit))\n        (let [n (bit-count bitmap)]\n          (cond\n            (< (* 2 n) (.-length arr))\n            (let [editable (.ensure-editable inode edit)\n                  earr     (.-arr editable)]\n              (aset added-leaf? 0 true)\n              (array-copy-downward earr (* 2 idx)\n                                   earr (* 2 (inc idx))\n                                   (* 2 (- n idx)))\n              (aset earr (* 2 idx) key)\n              (aset earr (inc (* 2 idx)) val)\n              (set! (.-bitmap editable) (bit-or (.-bitmap editable) bit))\n              editable)\n\n            (>= n 16)\n            (let [nodes (make-array 32)\n                  jdx   (mask hash shift)]\n              (aset nodes jdx (.inode-assoc! cljs.core.BitmapIndexedNode/EMPTY edit (+ shift 5) hash key val added-leaf?))\n              (loop [i 0 j 0]\n                (if (< i 32)\n                  (if (zero? (bit-and (bit-shift-right-zero-fill bitmap i) 1))\n                    (recur (inc i) j)\n                    (do (aset nodes i\n                              (if (coercive-not= nil (aget arr j))\n                                (.inode-assoc! cljs.core.BitmapIndexedNode/EMPTY\n                                               edit (+ shift 5) (cljs.core/hash (aget arr j)) (aget arr j) (aget arr (inc j)) added-leaf?)\n                                (aget arr (inc j))))\n                        (recur (inc i) (+ j 2))))))\n              (ArrayNode. edit (inc n) nodes))\n\n            :else\n            (let [new-arr (make-array (* 2 (+ n 4)))]\n              (array-copy arr 0 new-arr 0 (* 2 idx))\n              (aset new-arr (* 2 idx) key)\n              (aset added-leaf? 0 true)\n              (aset new-arr (inc (* 2 idx)) val)\n              (array-copy arr (* 2 idx) new-arr (* 2 (inc idx)) (* 2 (- n idx)))\n              (let [editable (.ensure-editable inode edit)]\n                (set! (.-arr editable) new-arr)\n                (set! (.-bitmap editable) (bit-or (.-bitmap editable) bit))\n                editable))))\n        (let [key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil)\n                (let [n (.inode-assoc! val-or-node edit (+ shift 5) hash key val added-leaf?)]\n                  (if (identical? n val-or-node)\n                    inode\n                    (edit-and-set inode edit (inc (* 2 idx)) n)))\n\n                (= key key-or-nil)\n                (if (identical? val val-or-node)\n                  inode\n                  (edit-and-set inode edit (inc (* 2 idx)) val))\n\n                :else\n                (do (aset added-leaf? 0 true)\n                    (edit-and-set inode edit (* 2 idx) nil (inc (* 2 idx))\n                                  (create-node edit (+ shift 5) key-or-nil val-or-node hash key val))))))))\n\n  (inode-without! [inode edit shift hash key removed-leaf?]\n    (let [bit (bitpos hash shift)]\n      (if (zero? (bit-and bitmap bit))\n        inode\n        (let [idx         (bitmap-indexed-node-index bitmap bit)\n              key-or-nil  (aget arr (* 2 idx))\n              val-or-node (aget arr (inc (* 2 idx)))]\n          (cond (coercive-= nil key-or-nil)\n                (let [n (.inode-without! val-or-node edit (+ shift 5) hash key removed-leaf?)]\n                  (cond (identical? n val-or-node) inode\n                        (coercive-not= nil n) (edit-and-set inode edit (inc (* 2 idx)) n)\n                        (== bitmap bit) nil\n                        :else (.edit-and-remove-pair inode edit bit idx)))\n                (= key key-or-nil)\n                (do (aset removed-leaf? 0 true)\n                    (.edit-and-remove-pair inode edit bit idx))\n                :else inode)))))\n\n  (kv-reduce [inode f init]\n    (inode-kv-reduce arr f init)))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [3583 3777],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L3583-L3777"},
 :full-name-encode "cljs.core_BitmapIndexedNode",
 :history [["+" "0.0-1211"]]}

```