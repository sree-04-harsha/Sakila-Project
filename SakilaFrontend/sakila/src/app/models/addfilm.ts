export interface Films {

  title: string;

  description: string;

  releaseYear: number;

  language: Language;

  rentalDuration: number;

  rentalRate: number;

  length: number;

  replacementCost: number;

  rating: string;

  specialFeatures: string;

  lastUpdate: String;

}



export interface Language {

  languageId: number;

}






//   export class UpdateMovieComponent {

//     newTitle: string;



//     // Rest of your component code

//   }




export interface Movie {

  title: string;

}