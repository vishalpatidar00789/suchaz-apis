/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { SuchazapisTestModule } from '../../../test.module';
import { CategoryImageSuchazDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz-delete-dialog.component';
import { CategoryImageSuchazService } from '../../../../../../main/webapp/app/entities/category-image-suchaz/category-image-suchaz.service';

describe('Component Tests', () => {

    describe('CategoryImageSuchaz Management Delete Component', () => {
        let comp: CategoryImageSuchazDeleteDialogComponent;
        let fixture: ComponentFixture<CategoryImageSuchazDeleteDialogComponent>;
        let service: CategoryImageSuchazService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [CategoryImageSuchazDeleteDialogComponent],
                providers: [
                    CategoryImageSuchazService
                ]
            })
            .overrideTemplate(CategoryImageSuchazDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CategoryImageSuchazDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategoryImageSuchazService);
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
