export interface Game{
  id: number;
    gameName:string;
    releaseDate:Date;
    publisherName:string;
    developerName:string;
    gameCover:string;
    gameTrailer:string;    
    gameDescription:string;
}
interface Rating {
  id: number;
  count: number;
  title: string;
}