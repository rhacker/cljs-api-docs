## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/unchecked-remainder-int

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-1798"><img valign="middle" alt="[+] 0.0-1798" src="https://img.shields.io/badge/+-0.0--1798-lightgrey.svg"></a> </td>
<td>
[<img height="24px" valign="middle" src="http://i.imgur.com/1GjPKvB.png"> <samp>clojure.core/unchecked-remainder-int</samp>](http://clojure.github.io/clojure/branch-master/clojure.core-api.html#clojure.core/unchecked-remainder-int)
</td>
</tr>
</table>

 <samp>
(__unchecked-remainder-int__ x n)<br>
</samp>

```
(no docstring)
```

---

 <pre>
clojurescript @ r1834
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:1541-1542](https://github.com/clojure/clojurescript/blob/r1834/src/cljs/cljs/core.cljs#L1541-L1542)</ins>
</pre>

```clj
(defn unchecked-remainder-int [x n]
  (cljs.core/unchecked-remainder-int x n))
```


---

 <pre>
clojurescript @ r1834
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:319-320](https://github.com/clojure/clojurescript/blob/r1834/src/clj/cljs/core.clj#L319-L320)</ins>
</pre>

```clj
(defmacro unchecked-remainder-int
  ([x n] `(mod ~x ~n)))
```

---

```clj
{:ns "cljs.core",
 :name "unchecked-remainder-int",
 :signature ["[x n]"],
 :shadowed-sources ({:code "(defmacro unchecked-remainder-int\n  ([x n] `(mod ~x ~n)))",
                     :filename "clojurescript/src/clj/cljs/core.clj",
                     :lines [319 320],
                     :link "https://github.com/clojure/clojurescript/blob/r1834/src/clj/cljs/core.clj#L319-L320"}),
 :history [["+" "0.0-1798"]],
 :type "function",
 :full-name-encode "cljs.core_unchecked-remainder-int",
 :source {:code "(defn unchecked-remainder-int [x n]\n  (cljs.core/unchecked-remainder-int x n))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [1541 1542],
          :link "https://github.com/clojure/clojurescript/blob/r1834/src/cljs/cljs/core.cljs#L1541-L1542"},
 :full-name "cljs.core/unchecked-remainder-int",
 :clj-symbol "clojure.core/unchecked-remainder-int"}

```