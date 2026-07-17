(ns association.facts
  "Industry rule/history catalog for the Federation of Egyptian
  Industries (FEI, اتحاد الصناعات المصرية) -- a 67th industry-
  association-level source (see cloud-itonami-assoc-9411-sau-fsc,
  -9411-aut-wko, -9411-irl-ibec, -9411-nzl-businessnz, -9411-cze-spcr,
  -9411-ind-cii, -9411-zaf-busa, -9411-bra-cni, -9411-ken-kam,
  -9411-can-chamber, -9411-mex-coparmex, -9411-ita-confindustria,
  -9411-nld-vnoncw, -9411-kor-kcci, -9411-arg-uia, -9411-bel-feb,
  -9411-dnk-di, -9411-swe-sn, -9411-fin-ek, -9411-tha-fti,
  -9411-chl-sofofa, -9411-col-andi, -9411-cri-uccaep, -9411-ecu-cip
  for the first twenty-four) per ADR-2607141700 (cloud-itonami-
  compliance-fact-federation). The TWENTY-FIFTH entry aligned to ISIC
  9411 (activities of business, employers, and professional
  membership organizations). Fills Egypt's previously-open
  association-axis gap (one of the 3-country gap list recorded at
  tick 159) -- Egypt now has real, individually verified facts across
  ALL THREE axes (country: cloud-itonami-iso3166-egy statute.facts;
  municipality: cloud-itonami-municipality-egy-cairo; association:
  this entry).

  fei.org.eg's own site, directly read, quotes verbatim (Arabic):
  'يقوم إتحاد الصناعات المصرية منذ أن نشأت فكرته في عام 1915' (the
  idea of the Federation of Egyptian Industries originated in 1915)
  -- this refers to the founding IDEA, not the formal establishment.
  Both catalog entries here are directly WebFetch-verified against
  ar.wikipedia.org's own article, which quotes verbatim: 'أنشأ سنة
  1922 تحت اسم \"رابطة الصناعات في مصر\"' (established in 1922 under
  the name 'League of Industries in Egypt') and 'في سنة 1947 صدور
  القانون رقم 73 لسنة 1947 بإنشاء اتحاد للغرف الصناعية تحت اسم
  \"اتحاد الصناعات المصرية\"' (in 1947, Law No. 73 of 1947 was issued
  establishing a federation of industrial chambers under the name
  'Federation of Egyptian Industries' -- its current name). No
  dedicated Wikidata entry exists for FEI itself (only a specific
  internal office, Q131883883, unrelated to the founding date) --
  noted transparently as a genuine data gap. No personal names of
  office-holders are persisted here.

  An association not in `catalog` has NO spec-basis, full stop; never
  fabricate one.")

(def catalog
  "association-slug -> vector of association-rule entries."
  {"fei"
   [{:association-rule/id "fei.founding-1922-league-of-industries"
     :association-rule/title "Established in 1922 under the name 'رابطة الصناعات في مصر' (League of Industries in Egypt) -- the formal founding, distinct from the 1915 origin of the idea per fei.org.eg's own site (ar.wikipedia.org)"
     :association-rule/association "fei"
     :association-rule/isic "9411"
     :association-rule/country "EGY"
     :association-rule/kind :governance-program
     :association-rule/url "https://ar.wikipedia.org/wiki/%D8%A7%D8%AA%D8%AD%D8%A7%D8%AF_%D8%A7%D9%84%D8%B5%D9%86%D8%A7%D8%B9%D8%A7%D8%AA_%D8%A7%D9%84%D9%85%D8%B5%D8%B1%D9%8A%D8%A9"
     :association-rule/url-provenance :wikipedia-corroborated
     :association-rule/established-date "1922"
     :association-rule/retrieved-at "2026-07-18"
     :association-rule/topic #{:governance}}
    {:association-rule/id "fei.law-73-1947-current-name"
     :association-rule/title "Law No. 73 of 1947 established a federation of industrial chambers under the name 'اتحاد الصناعات المصرية' (Federation of Egyptian Industries), its current name (ar.wikipedia.org)"
     :association-rule/association "fei"
     :association-rule/isic "9411"
     :association-rule/country "EGY"
     :association-rule/kind :governance-program
     :association-rule/url "https://ar.wikipedia.org/wiki/%D8%A7%D8%AA%D8%AD%D8%A7%D8%AF_%D8%A7%D9%84%D8%B5%D9%86%D8%A7%D8%B9%D8%A7%D8%AA_%D8%A7%D9%84%D9%85%D8%B5%D8%B1%D9%8A%D8%A9"
     :association-rule/url-provenance :wikipedia-corroborated
     :association-rule/established-date "1947"
     :association-rule/retrieved-at "2026-07-18"
     :association-rule/topic #{:governance}}]})

(defn spec-basis [association] (get catalog association))

(defn coverage
  ([] (coverage (keys catalog)))
  ([associations]
   (let [have (filter catalog associations)
         missing (remove catalog associations)]
     {:requested (count associations)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-9411-egy-fei Wave 0 (ADR-2607141700): "
                 (count (get catalog "fei")) " FEI entries seeded "
                 "with fei.org.eg's own site (1915 idea-origin) + Arabic Wikipedia "
                 "(1922 formal founding, 1947 current-name legal establishment) corroboration. "
                 "No dedicated Wikidata entry exists for FEI itself. "
                 "Extend `association.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [association topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis association)))
