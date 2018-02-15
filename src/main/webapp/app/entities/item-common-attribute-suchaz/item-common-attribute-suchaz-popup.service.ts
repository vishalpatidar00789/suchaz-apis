import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { ItemCommonAttributeSuchaz } from './item-common-attribute-suchaz.model';
import { ItemCommonAttributeSuchazService } from './item-common-attribute-suchaz.service';

@Injectable()
export class ItemCommonAttributeSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private itemCommonAttributeService: ItemCommonAttributeSuchazService

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
                this.itemCommonAttributeService.find(id)
                    .subscribe((itemCommonAttributeResponse: HttpResponse<ItemCommonAttributeSuchaz>) => {
                        const itemCommonAttribute: ItemCommonAttributeSuchaz = itemCommonAttributeResponse.body;
                        this.ngbModalRef = this.itemCommonAttributeModalRef(component, itemCommonAttribute);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.itemCommonAttributeModalRef(component, new ItemCommonAttributeSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    itemCommonAttributeModalRef(component: Component, itemCommonAttribute: ItemCommonAttributeSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.itemCommonAttribute = itemCommonAttribute;
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
