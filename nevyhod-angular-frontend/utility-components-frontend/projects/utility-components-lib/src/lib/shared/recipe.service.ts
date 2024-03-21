import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class RecipeService {
    constructor() {

    }

    public getRecipes() {
        return [
            { name: 'green4', price: 123 },
            { name: 'green5', price: 123 },
            { name: 'green6', price: 123 },
        ];
    }
}