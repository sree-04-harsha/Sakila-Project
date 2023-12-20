export interface Film {
  filmId: number;
  title: string;
  description: string;
  releaseYear: number;
  language: Language;
  originalLanguage: Language;
  rentalDuration: number;
  rentalRate: number;
  length: number;
  replacementCost: number;
  rating: string;
  specialFeatures: string;
  lastUpdate: string;
  actors: Actor[];
  categories: Category[];
}

export interface Actor {
  actorId: number;
  firstName: string;
  lastName: string;
  lastUpdate: string;
  films: Film[];
}

export interface Category {
  categoryId: number;
  name: string;
  lastUpdate: string;
  films: Film[];
}
export interface Language {
  languageId: number;
  name: string;
  films: Film[];
  originalLanguageFilms: Film[];
  lastUpdate: string;
}