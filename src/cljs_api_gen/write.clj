(ns cljs-api-gen.write
  (:require
    [clojure.set :refer [rename-keys]]
    [clojure.string :refer [join]]
    [cljs-api-gen.repo-cljs :refer [cljs-tag->version]]
    [cljs-api-gen.config :refer [*output-dir*
                                 docs-dir
                                 edn-output-file]]
    [cljs-api-gen.util :refer [symbol->filename mapmap]]
    ))

(defn item-filename
  [item]
  (str *output-dir* "/" docs-dir "/" (:ns item) "_" (symbol->filename (:name item))))

(defn make-history-text
  [text]
  (let [plus-or-minus (first text)
        change (if (= \+ plus-or-minus) "Added" "Removed")
        tag (subs text 1)
        version (cljs-tag->version tag)]
    (str change " in " version)))

(defn cljsdoc-section
  [title content]
  (when content
    (str "===== " title "\n" content "\n")))

(defn make-cljsdoc
  [item]
  (join "\n"
    (keep identity
      [(cljsdoc-section "Name" (:full-name item))
       (cljsdoc-section "Type" (:type item))
       (cljsdoc-section "Return Type" (:return-type item))
       (cljsdoc-section "Clojure" (:clj-symbol item))
       (cljsdoc-section "Docstring" (:docstring item))
       (cljsdoc-section "Signature" (join "\n" (:signature item)))
       (cljsdoc-section "Filename" (:source-filename item))
       (cljsdoc-section "Source" (:source item))
       (cljsdoc-section "Github" (:source-link item))
       (cljsdoc-section "History" (join "\n" (map make-history-text (:history item))))
       ""])))

(defn dump-doc-file!
  [item]
  (let [filename (str (item-filename item) ".cljsdoc")
        cljsdoc-content (make-cljsdoc item)]
    (spit filename cljsdoc-content) ;; use `:append true` to see overwrites
    ))

(defn dump-clj-not-cljs-file!
  [clj-not-cljs]
  (let [content (->> clj-not-cljs
                     (group-by #(namespace (symbol %)))
                     (mapmap #(join "\n" (sort %)))
                     (sort-by first)
                     (map second)
                     (join "\n\n"))
        outfile (str *output-dir* "/not-in-cljs")]
    (spit outfile content)))

(defn dump-edn-file!
  [result]
  (let [outfile (str *output-dir* "/" edn-output-file)]
    (spit outfile (pr-str result))))

(defn dump-result!
  [result]
  (doseq [item (vals (:symbols (:library-api result)))]
    (dump-doc-file! item))
  (dump-clj-not-cljs-file! (:clj-not-cljs result))
  (dump-edn-file! result))
