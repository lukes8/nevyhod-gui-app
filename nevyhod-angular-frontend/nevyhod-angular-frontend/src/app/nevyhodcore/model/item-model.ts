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

    constructor(
        id: number,
        email: string,
        title: string,
        category: string,
        description: string,
        price: number,
        createdDate: Date,
        amount: number,
        status: number,
        disabled: boolean,
        imagePath: string
    ) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
        this.createdDate = createdDate;
        this.amount = amount;
        this.status = status;
        this.disabled = disabled;
        this.imagePath = imagePath;
    }

}