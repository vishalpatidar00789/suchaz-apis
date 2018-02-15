import { Component, OnInit, OnDestroy, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserProfileSuchaz } from './user-profile-suchaz.model';
import { UserProfileSuchazPopupService } from './user-profile-suchaz-popup.service';
import { UserProfileSuchazService } from './user-profile-suchaz.service';
import { SuchAzUserSuchaz, SuchAzUserSuchazService } from '../such-az-user-suchaz';

@Component({
    selector: 'jhi-user-profile-suchaz-dialog',
    templateUrl: './user-profile-suchaz-dialog.component.html'
})
export class UserProfileSuchazDialogComponent implements OnInit {

    userProfile: UserProfileSuchaz;
    isSaving: boolean;

    suchazusers: SuchAzUserSuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private userProfileService: UserProfileSuchazService,
        private suchAzUserService: SuchAzUserSuchazService,
        private elementRef: ElementRef,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.suchAzUserService
            .query({filter: 'userprofile-is-null'})
            .subscribe((res: HttpResponse<SuchAzUserSuchaz[]>) => {
                if (!this.userProfile.suchAzUserId) {
                    this.suchazusers = res.body;
                } else {
                    this.suchAzUserService
                        .find(this.userProfile.suchAzUserId)
                        .subscribe((subRes: HttpResponse<SuchAzUserSuchaz>) => {
                            this.suchazusers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.userProfile, this.elementRef, field, fieldContentType, idInput);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userProfile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userProfileService.update(this.userProfile));
        } else {
            this.subscribeToSaveResponse(
                this.userProfileService.create(this.userProfile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserProfileSuchaz>>) {
        result.subscribe((res: HttpResponse<UserProfileSuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserProfileSuchaz) {
        this.eventManager.broadcast({ name: 'userProfileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackSuchAzUserById(index: number, item: SuchAzUserSuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-profile-suchaz-popup',
    template: ''
})
export class UserProfileSuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userProfilePopupService: UserProfileSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userProfilePopupService
                    .open(UserProfileSuchazDialogComponent as Component, params['id']);
            } else {
                this.userProfilePopupService
                    .open(UserProfileSuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
