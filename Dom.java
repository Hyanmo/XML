import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomParser {

    public static void main(String[] args) {
        try {
            // 创建DocumentBuilderFactory实例
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder实例
            DocumentBuilder paseur = factory.newDocumentBuilder();
            // 解析XML文件
            Document document = paseur.parse(new File("example.xml"));
            // 获取根元素
            Element rootElement = document.getDocumentElement();
            System.out.println("Root element: " + rootElement.getNodeName());

            // 获取所有子节点
            NodeList nodeList = rootElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Element: " + element.getNodeName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //a)Nombre d'element dans un document
    static int nomberElement(Node noeud){
        int nbe=0;
        if(noeud.getNodeType==Node.ELEMENT_NODE)//pour elementA ajoute &&noeud.getNodeName().equals("A")
            nbe++;
        if(noeud.hasChildNodes()){
            NodeList enfants=noeud.getChildNodes();
            for(int i=0;i<enfants.getLength();i++){
                int reseaul=nomberElement(enfants.item(i));
                nbe+=reseaul;
            }
        }
        return nbe;
    }

    static int nomberElementA(Node noeud){
        NodeList elements=((Element)noeud.getElementByTagName("A"));
        if(noeud.getNodeName().equals("A"))
            return 1+elements.getLength();
        else
            return elements.getLength();
    }

    //b)Afficher text dans un document
    static void afficherTexte(Node noeud){
        if(noeud.getNodeType()==Node.TEXT_NODE)//pour contenant "politique" ajoute && node.getNodeValue().contains("politique")
            System.out.println(noeud.getNodeValue());
        if(noeud.hasChildNodes()){
            NodeList enfants=noeud.getChildNodes();
            for(int i=0;i<enfants.getLength();i++){
                afficherTexte(enfants.item(i));
            }
        }
    }

    static void afficherTexte2(Node noeud){
        System.out.printLn(noeud.getTextContent()); 
    }

    //Affiche la somme des valeurs de tous les éléments « montant » fils d’un élément « dépense »
    static int sommeDepenses(Node noeud){
        int somme=0;
        if(noeud.getNodeType()==Node.ELEMENT_NODE&&noeud.getNodeName().equals("montant")){
            if(noeud.getParentNode().getNodeName().equals("depense")){
                int montant=Integet.paresInt(noeud.getTextContent())
                somme+=montant;
            }
        }
        if(noeud.hasChildNodes()){
            NodeList enfants=noeud.getChildNodes();
            for(int i=0;i<enfants.getLength();i++){
                sommeDepenses(enfants.item(i));
            }
        }
        return somme;
    }
}