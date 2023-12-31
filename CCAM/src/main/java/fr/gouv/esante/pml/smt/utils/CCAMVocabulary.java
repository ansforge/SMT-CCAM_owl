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


public enum CCAMVocabulary implements HasShortForm, HasIRI, HasPrefixedName {
//@formatter:off
    /** action.         */ action          ("action",            ANNOTATION_PROPERTY),
    /** modeAcces.         */ modeAcces          ("modeAcces",            ANNOTATION_PROPERTY),
    /** topographie.         */ topographie          ("topographie",            ANNOTATION_PROPERTY),
    /** acte.         */ acte          ("acte",            ANNOTATION_PROPERTY),
    /** activite.         */ activite          ("activite",            ANNOTATION_PROPERTY),
    /** acteActivite.         */ acteActivite          ("acteActivite",            ANNOTATION_PROPERTY),
    /** phase.         */ phase          ("phase",            ANNOTATION_PROPERTY),
    /** partieActeActivite.         */ partieActeActivite          ("acteActivite",            ANNOTATION_PROPERTY),
    /** partieActeActivitePhase.         */ ActeActivitePhase          ("acteActivitePhase",            ANNOTATION_PROPERTY),
    /** modificateur.         */ modificateur          ("modificateur",            ANNOTATION_PROPERTY),
    /** acteExclus.         */ acteExclus          ("acteExclus",            ANNOTATION_PROPERTY),
    /** incompatibilite.         */ incompatibilite          ("incompatibilite",            ANNOTATION_PROPERTY),
    /** acteAssocie.         */ acteAssocie          ("acteAssocie",            ANNOTATION_PROPERTY),
    /** procedure.         */ procedure          ("procedure",            ANNOTATION_PROPERTY),
    /** acteDeLaProcedure.         */ acteDeLaProcedure          ("acteDeLaProcedure",            ANNOTATION_PROPERTY),
    /** typeActe.         */ typeActe          ("typeActe",            ANNOTATION_PROPERTY),
    /** indicationSpecifique.         */ indicationSpecifique          ("indicationSpecifique",            ANNOTATION_PROPERTY),
    /** formationSpecifique.         */ formationSpecifique          ("formationSpecifique",            ANNOTATION_PROPERTY),
    /** environnementSpecifique.         */ environnementSpecifique          ("environnementSpecifique",            ANNOTATION_PROPERTY),
    /** receuilProspectifDeDonnees.         */ receuilProspectifDeDonnees          ("receuilProspectifDeDonnees",            ANNOTATION_PROPERTY),
    /** facturation.         */ facturation          ("facturation",            ANNOTATION_PROPERTY),
    /** dentIncompatible.         */ dentIncompatible          ("dentIncompatible",            ANNOTATION_PROPERTY),
    /** verbeAction.         */ verbeAction          ("verbeAction",            ANNOTATION_PROPERTY),
    
    
    /** @deprecated No longer used */
    DOCUMENT("Document", CLASS),
    /** @deprecated No longer used */
    IMAGE("Image", CLASS),
    /** @deprecated No longer used */
    COLLECTABLEPROPERTY("CollectableProperty", ANNOTATION_PROPERTY),
    /** @deprecated No longer used */
    RESOURCE("Resource", CLASS),
    /** @deprecated No longer used */
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

  CCAMVocabulary(final String localname, final EntityType<?> entityType) {
    this.localName = localname;
    this.prefixedName = NameSpace.CCAM.getPrefixName() + ':' + localname;
    this.entityType = entityType;
    this.iri = IRI.create(NameSpace.CCAM.toString(), localname);
  }

  private static Stream<CCAMVocabulary> stream() {
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
