import { Directive, ElementRef, EventEmitter, HostListener, Output } from '@angular/core';
@Directive({
    selector: '[clickOutside]'
})
export class SubtitleDirective {
    // @HostListener('mouseenter') onMouseEnter() {
    //     alert("You just hovered over a paragraph");
    // }
    // @HostListener('mousedown') onMouseDown() {
    //     alert("You just clicked a paragraph");
    // }


    @Output()
    clickOutside: EventEmitter<Event> = new EventEmitter<Event>();

    @HostListener('document:mousedown', ['$event'])
    onGlobalClick(event: MouseEvent): void {
        if (!this.elemRef.nativeElement.contains(event.target)) {
            // Cliecked Outside
            this.clickOutside.emit(event);
            // alert("You just clicked global");
        }
    }

    constructor(private elemRef: ElementRef) {
    }
}