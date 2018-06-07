
TIES concept codes
------------------

How are reports coded in TIES?
TIES uses NOBLE Coder a generic tool and API that auto codes free text with concepts from controlled terminology, using NCI Metathesaurus (https://ncim.nci.nih.gov/ncimbrowser/). Its algorithm is similar to IndexFinder or ConceptMapper. Noble Coder is part of the Noble Tools Suite, a set of Natural Language Processing (NLP) tools and Application Programming Interfaces (API) written in Java for interfacing with ontologies, auto coding text and extracting information. The Noble Tools suite also includes a generic ontology API for interfacing with Web Ontology Language (OWL) files, OBO and BioPortal ontologies and a number of support utilities and methods useful for NLP (e.g. string normalization, ngram and stemming)


Data File
---------

Interpretation of the prefix on the Concept CUI codes:
- 'A' denotes a positive assertion (e.g., AC0193842)
- 'N' denote a negated assertion  (e.g., NC0006663)

Thus, a CUI has two possible values in a report: positive (AC…) or negation (NC…)

- Each row in the file represents a patient
- Column A is the internal TIES ID
- Column B is the TCGA ID
- Columns C though xxx represent the concept code list for the encoded document

* if a row is blank, no encoded reports were available
