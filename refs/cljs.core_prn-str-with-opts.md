## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/prn-str-with-opts

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-1011"><img valign="middle" alt="[+] 0.0-1011" src="https://img.shields.io/badge/+-0.0--1011-lightgrey.svg"></a> </td>
</tr>
</table>

 <samp>
(__prn-str-with-opts__ objs opts)<br>
</samp>

```
Same as pr-str-with-opts followed by (newline)
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:5667-5672](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L5667-L5672)</ins>
</pre>

```clj
(defn prn-str-with-opts
  [objs opts]
  (let [sb (pr-sb objs opts)]
    (.append sb \newline)
    (str sb)))
```


---

```clj
{:full-name "cljs.core/prn-str-with-opts",
 :ns "cljs.core",
 :name "prn-str-with-opts",
 :docstring "Same as pr-str-with-opts followed by (newline)",
 :type "function",
 :signature ["[objs opts]"],
 :source {:code "(defn prn-str-with-opts\n  [objs opts]\n  (let [sb (pr-sb objs opts)]\n    (.append sb \\newline)\n    (str sb)))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [5667 5672],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L5667-L5672"},
 :full-name-encode "cljs.core_prn-str-with-opts",
 :history [["+" "0.0-1011"]]}

```