import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SuchAzMenuSuchaz } from './such-az-menu-suchaz.model';
import { SuchAzMenuSuchazService } from './such-az-menu-suchaz.service';

@Injectable()
export class SuchAzMenuSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private suchAzMenuService: SuchAzMenuSuchazService

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
                this.suchAzMenuService.find(id)
                    .subscribe((suchAzMenuResponse: HttpResponse<SuchAzMenuSuchaz>) => {
                        const suchAzMenu: SuchAzMenuSuchaz = suchAzMenuResponse.body;
                        this.ngbModalRef = this.suchAzMenuModalRef(component, suchAzMenu);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.suchAzMenuModalRef(component, new SuchAzMenuSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    suchAzMenuModalRef(component: Component, suchAzMenu: SuchAzMenuSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.suchAzMenu = suchAzMenu;
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
