import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CategorySuchaz } from './category-suchaz.model';
import { CategorySuchazPopupService } from './category-suchaz-popup.service';
import { CategorySuchazService } from './category-suchaz.service';

@Component({
    selector: 'jhi-category-suchaz-dialog',
    templateUrl: './category-suchaz-dialog.component.html'
})
export class CategorySuchazDialogComponent implements OnInit {

    category: CategorySuchaz;
    isSaving: boolean;

    categories: CategorySuchaz[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private categoryService: CategorySuchazService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.categoryService.query()
            .subscribe((res: HttpResponse<CategorySuchaz[]>) => { this.categories = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.category.id !== undefined) {
            this.subscribeToSaveResponse(
                this.categoryService.update(this.category));
        } else {
            this.subscribeToSaveResponse(
                this.categoryService.create(this.category));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CategorySuchaz>>) {
        result.subscribe((res: HttpResponse<CategorySuchaz>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CategorySuchaz) {
        this.eventManager.broadcast({ name: 'categoryListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCategoryById(index: number, item: CategorySuchaz) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-category-suchaz-popup',
    template: ''
})
export class CategorySuchazPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private categoryPopupService: CategorySuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.categoryPopupService
                    .open(CategorySuchazDialogComponent as Component, params['id']);
            } else {
                this.categoryPopupService
                    .open(CategorySuchazDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
