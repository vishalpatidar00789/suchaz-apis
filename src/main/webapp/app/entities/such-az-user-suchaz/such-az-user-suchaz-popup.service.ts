import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SuchAzUserSuchaz } from './such-az-user-suchaz.model';
import { SuchAzUserSuchazService } from './such-az-user-suchaz.service';

@Injectable()
export class SuchAzUserSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private suchAzUserService: SuchAzUserSuchazService

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
                this.suchAzUserService.find(id)
                    .subscribe((suchAzUserResponse: HttpResponse<SuchAzUserSuchaz>) => {
                        const suchAzUser: SuchAzUserSuchaz = suchAzUserResponse.body;
                        this.ngbModalRef = this.suchAzUserModalRef(component, suchAzUser);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.suchAzUserModalRef(component, new SuchAzUserSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    suchAzUserModalRef(component: Component, suchAzUser: SuchAzUserSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.suchAzUser = suchAzUser;
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
