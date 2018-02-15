import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ItemAttributeTypeSuchaz } from './item-attribute-type-suchaz.model';
import { ItemAttributeTypeSuchazService } from './item-attribute-type-suchaz.service';

@Injectable()
export class ItemAttributeTypeSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private itemAttributeTypeService: ItemAttributeTypeSuchazService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.itemAttributeTypeService.find(id)
                    .subscribe((itemAttributeTypeResponse: HttpResponse<ItemAttributeTypeSuchaz>) => {
                        const itemAttributeType: ItemAttributeTypeSuchaz = itemAttributeTypeResponse.body;
                        this.ngbModalRef = this.itemAttributeTypeModalRef(component, itemAttributeType);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.itemAttributeTypeModalRef(component, new ItemAttributeTypeSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    itemAttributeTypeModalRef(component: Component, itemAttributeType: ItemAttributeTypeSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.itemAttributeType = itemAttributeType;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
