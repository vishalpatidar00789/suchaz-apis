/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { ItemReviewSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz-delete-dialog.component';
import { ItemReviewSuchazService } from '../../../../../../main/webapp/app/entities/item-review-suchaz/item-review-suchaz.service';

describe('Component Tests', () => {

    describe('ItemReviewSuchaz Management Delete Component', () => {
        let comp: ItemReviewSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<ItemReviewSuchazDeleteDialogComponent>;
        let service: ItemReviewSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [ItemReviewSuchazDeleteDialogComponent],
                providers: [
                    ItemReviewSuchazService
                ]
            })
            .overrideTemplate(ItemReviewSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ItemReviewSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ItemReviewSuchazService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
