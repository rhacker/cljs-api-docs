## cljs.core/ES6Iterator



 <table border="1">
<tr>
<td>type</td>
<td><a href="https://github.com/cljsinfo/cljs-api-docs/tree/0.0-2371"><img valign="middle" alt="[+] 0.0-2371" title="Added in 0.0-2371" src="https://img.shields.io/badge/+-0.0--2371-lightgrey.svg"></a> </td>
</tr>
</table>


 <samp>
(__ES6Iterator.__ s)<br>
</samp>

---







Source code @ [github](https://github.com/clojure/clojurescript/blob/r2411/src/cljs/cljs/core.cljs#L709-L716):

```clj
(deftype ES6Iterator [^:mutable s]
  Object
  (next [_]
    (if-not (nil? s)
      (let [x (first s)]
        (set! s (next s))
        #js {:value x :done false})
      #js {:value nil :done true})))
```

<!--
Repo - tag - source tree - lines:

 <pre>
clojurescript @ r2411
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:709-716](https://github.com/clojure/clojurescript/blob/r2411/src/cljs/cljs/core.cljs#L709-L716)</ins>
</pre>

-->

---



###### External doc links:

[`cljs.core/ES6Iterator` @ crossclj](http://crossclj.info/fun/cljs.core.cljs/ES6Iterator.html)<br>

---

 <table>
<tr><td>
<img valign="middle" align="right" width="48px" src="http://i.imgur.com/Hi20huC.png">
</td><td>
Created for the upcoming ClojureScript website.<br>
[edit here] | [learn how]
</td></tr></table>

[edit here]:https://github.com/cljsinfo/cljs-api-docs/blob/master/cljsdoc/cljs.core/ES6Iterator.cljsdoc
[learn how]:https://github.com/cljsinfo/cljs-api-docs/wiki/cljsdoc-files

<!--

This information was too distracting to show to readers, but I'll leave it
commented here since it is helpful to:

- pretty-print the data used to generate this document
- and show how to retrieve that data



The API data for this symbol:

```clj
{:ns "cljs.core",
 :name "ES6Iterator",
 :type "type",
 :signature ["[s]"],
 :source {:code "(deftype ES6Iterator [^:mutable s]\n  Object\n  (next [_]\n    (if-not (nil? s)\n      (let [x (first s)]\n        (set! s (next s))\n        #js {:value x :done false})\n      #js {:value nil :done true})))",
          :title "Source code",
          :repo "clojurescript",
          :tag "r2411",
          :filename "src/cljs/cljs/core.cljs",
          :lines [709 716]},
 :full-name "cljs.core/ES6Iterator",
 :full-name-encode "cljs.core/ES6Iterator",
 :history [["+" "0.0-2371"]]}

```

Retrieve the API data for this symbol:

```clj
;; from Clojure REPL
(require '[clojure.edn :as edn])
(-> (slurp "https://raw.githubusercontent.com/cljsinfo/cljs-api-docs/catalog/cljs-api.edn")
    (edn/read-string)
    (get-in [:symbols "cljs.core/ES6Iterator"]))
```

-->