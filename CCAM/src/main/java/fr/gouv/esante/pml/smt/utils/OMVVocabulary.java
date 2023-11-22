package fr.gouv.esante.pml.smt.utils;

import static org.semanticweb.owlapi.model.EntityType.ANNOTATION_PROPERTY;
import static org.semanticweb.owlapi.model.EntityType.CLASS;
import static org.semanticweb.owlapi.model.EntityType.DATA_PROPERTY;
import static org.semanticweb.owlapi.model.EntityType.OBJECT_PROPERTY;
import static org.semanticweb.owlapi.util.OWLAPIStreamUtils.asSet;
import java.util.Set;
import java.util.stream.Stream;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.HasIRI;
import org.semanticweb.owlapi.model.HasPrefixedName;
import org.semanticweb.owlapi.model.HasShortForm;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.providers.AnnotationPropertyProvider;
import org.semanticweb.owlapi.vocab.Namespaces;


public enum OMVVocabulary implements HasShortForm, HasIRI, HasPrefixedName {
//@formatter:off
    /** acronym.         */ acronym            ("acronym",            ANNOTATION_PROPERTY),
    /** conformsToKnowledgeRepresentationParadigm.         */ conformsToKnowledgeRepresentationParadigm            ("conformsToKnowledgeRepresentationParadigm",            ANNOTATION_PROPERTY),
    /** hasFormalityLevel.         */ hasFormalityLevel            ("hasFormalityLevel",            ANNOTATION_PROPERTY),
    /** hasOntologyLanguage.         */ hasOntologyLanguage            ("hasOntologyLanguage",            ANNOTATION_PROPERTY),
    /** hasOntologySyntax.         */ hasOntologySyntax            ("hasOntologySyntax",            ANNOTATION_PROPERTY),
    /** isOfType.         */ isOfType            ("isOfType",            ANNOTATION_PROPERTY),
    /** keyClasses.         */ keyClasses            ("keyClasses",            ANNOTATION_PROPERTY),
    /** numberOfAxioms.         */ numberOfAxioms            ("numberOfAxioms",            ANNOTATION_PROPERTY),
    /** numberOfClasses.         */ numberOfClasses            ("numberOfClasses",            ANNOTATION_PROPERTY),
    /** resourceLocator.         */ resourceLocator            ("resourceLocator",            ANNOTATION_PROPERTY),
    /** status.         */ status            ("status",            ANNOTATION_PROPERTY),
    /** usedOntologyEngineeringTool.         */ usedOntologyEngineeringTool            ("usedOntologyEngineeringTool",            ANNOTATION_PROPERTY),
   

    /** @deprecated No longer used */
    @Deprecated
    DOCUMENT("Document", CLASS),
    /** @deprecated No longer used */
    @Deprecated
    IMAGE("Image", CLASS),
    /** @deprecated No longer used */
    @Deprecated
    COLLECTABLEPROPERTY("CollectableProperty", ANNOTATION_PROPERTY),
    /** @deprecated No longer used */
    @Deprecated
    RESOURCE("Resource", CLASS),
    /** @deprecated No longer used */
    @Deprecated
    COMMENT("comment", ANNOTATION_PROPERTY);
//@formatter:on
  /**
   * All IRIs.
   */
  public static final Set<IRI> ALL_IRIS = asSet(stream().map(v -> v.getIRI()));
  private final String localName;
  private final IRI iri;
  private final EntityType<?> entityType;
  private final String prefixedName;

  OMVVocabulary(final String localname, final EntityType<?> entityType) {
    this.localName = localname;
    this.prefixedName = NameSpace.OMV.getPrefixName() + ':' + localname;
    this.entityType = entityType;
    this.iri = IRI.create(NameSpace.OMV.toString(), localname);
  }

  private static Stream<OMVVocabulary> stream() {
    return Stream.of(values());
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA annotation properties
   */
  public static Set<OWLAnnotationProperty> getAnnotationProperties(
      final AnnotationPropertyProvider dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(ANNOTATION_PROPERTY))
        .map(v -> dataFactory.getOWLAnnotationProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA object properties
   */
  public static Set<OWLObjectProperty> getObjectProperties(final OWLDataFactory dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(OBJECT_PROPERTY))
        .map(v -> dataFactory.getOWLObjectProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA data properties
   */
  public static Set<OWLDataProperty> getDataProperties(final OWLDataFactory dataFactory) {
    return asSet(stream().filter(v -> v.entityType.equals(DATA_PROPERTY))
        .map(v -> dataFactory.getOWLDataProperty(v.iri)));
  }

  /**
   * @param dataFactory data factory to use
   * @return set of SCHEMA classes
   */
  public static Set<OWLClass> getClasses(final OWLDataFactory dataFactory) {
    return asSet(
        stream().filter(v -> v.entityType.equals(CLASS)).map(v -> dataFactory.getOWLClass(v.iri)));
  }

  /**
   * @return entity type
   */
  public EntityType<?> getEntityType() {
    return this.entityType;
  }

  /**
   * @return local name
   */
  public String getLocalName() {
    return this.localName;
  }

  @Override
  public IRI getIRI() {
    return this.iri;
  }

  @Override
  public String getShortForm() {
    return this.localName;
  }

  @Override
  public String getPrefixedName() {
    return this.prefixedName;
  }
}
