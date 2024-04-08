import { map } from "d3";

export class ItemVO {
    id: number;
    email: string;
    title: string;
    category: string;
    description: string;
    price: number;
    createdDate: Date;
    amount: number;
    status: number;
    disabled: boolean;
    imagePath: string;
    mapUrl: string;

    constructor(builder: ItemBuilder) {
        this.id = builder.id;
        this.email = builder.email;
        this.title = builder.title;
        this.category = builder.category;
        this.description = builder.description;
        this.price = builder.price;
        this.createdDate = builder.createdDate;
        this.amount = builder.amount;
        this.status = builder.status;
        this.disabled = builder.disabled;
        this.imagePath = builder.imagePath;
        this.mapUrl = builder.mapUrl;
    }

}


export class ItemBuilder {
    id: number;
    email: string;
    title: string;
    category: string;
    description: string;
    price: number;
    createdDate: Date;
    amount: number;
    status: number;
    disabled: boolean;
    imagePath: string;
    mapUrl: string;

    constructor(id: number) {
        this.id = id;
    }
    withEmail(email: string) {
        this.email = email;
        return this;
    }
    withTitle(title: string) {
        this.title = title;
        return this;
    }
    withCategory(category: string) {
        this.category = category;
        return this;
    }
    withDescription(description: string) {
        this.description = description;
        return this;
    }
    withPrice(price: number) {
        this.price = price;
        return this;
    }
    withCreatedDate(createdDate: Date) {
        this.createdDate = createdDate;
        return this;
    }
    withAmount(amount: number) {
        this.amount = amount;
        return this;
    }
    withStatus(status: number) {
        this.status = status;
        return this;
    }
    withDisabled(disabled: boolean) {
        this.disabled = disabled;
        return this;
    }
    withImagePath(imagePath: string) {
        this.imagePath = imagePath;
        return this;
    }
    withMapUrl(mapUrl: string) {
        this.mapUrl = mapUrl;
        return this;
    }
}